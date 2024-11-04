package com.fengshui.common.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fengshui.common.repository.postgresql.IAdvertisementPackageRepository;
import com.fengshui.common.repository.postgresql.IAppUserRepository;
import com.fengshui.common.repository.postgresql.IPaymentRepository;
import com.fengshui.common.repository.postgresql.dto.PaymentDTO;
import com.fengshui.common.repository.postgresql.entities.AdvertisementPackageEntity;
import com.fengshui.common.repository.postgresql.entities.AppUserEntity;
import com.fengshui.common.repository.postgresql.entities.PaymentEntity;
import com.fengshui.common.repository.postgresql.enums.PaymentStatus;
import com.fengshui.common.repository.postgresql.mapper.PaymentMapper;
import com.fengshui.common.services.PaymentService;
import com.fengshui.common.shared.Request.Payment.CreatePaymentRequestModel;
import com.fengshui.common.shared.Request.Payment.GetPaymentByIdRequestModel;
import com.fengshui.common.shared.Response.Payment.CreatePaymentResponseModel;
import com.fengshui.common.shared.Response.Payment.GetPaymentByIdResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.PaymentData;
import vn.payos.type.Webhook;
import vn.payos.type.WebhookData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("http://" + "${public.api.url}" + ":3000/information/ads-package")
    private String returnUrl;
    @Value("http://" + "${public.api.url}" + ":3000/information/ads-package")
    private String cancelUrl;

    @Autowired
    private PayOS payOS;

    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private IAdvertisementPackageRepository adsPackageRepository;

    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public ResponseEntity<CreatePaymentResponseModel> createPayment(CreatePaymentRequestModel requestModel) {
        CreatePaymentResponseModel response;

        String currentTimeString = String.valueOf(new Date().getTime());
        long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));

        // Retrieve user from the repository
        Optional<AppUserEntity> appUserOptional = appUserRepository.findById(requestModel.getAppUserId());
        if (appUserOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + requestModel.getAppUserId());
        }
        Optional<AdvertisementPackageEntity> adsPackage = adsPackageRepository.findById(requestModel.getAdsPackageId());
        if (appUserOptional.isEmpty()) {
            throw new RuntimeException("Ads package not found with ID: " + requestModel.getAdsPackageId());
        }
        // Build payment data for creating a payment link
        PaymentData paymentData = PaymentData.builder()
                .orderCode(orderCode)
                .description(requestModel.getNote())
                .amount(requestModel.getTotalAmount().intValue())  // Assuming amount is a BigDecimal
                .item(requestModel.getItem())
                .returnUrl(returnUrl)
                .cancelUrl(cancelUrl)
                .build();

        CheckoutResponseData data;
        try {
            // Create payment link
            data = payOS.createPaymentLink(paymentData);

            // Map response data to PaymentEntity and save to repository
            PaymentEntity payment = PaymentEntity.builder()
                    .paymentLinkId(data.getPaymentLinkId())
                    .checkoutUrl(data.getCheckoutUrl())
                    .currency(data.getCurrency())
                    .user(appUserOptional.get())
                    .note(data.getDescription())
                    .amount(BigDecimal.valueOf(data.getAmount()))
                    .advertisementPackage(adsPackage.get())
                    .orderCode(data.getOrderCode())
                    .createdAt(LocalDateTime.now())
                    .build();

            paymentRepository.save(payment);
            response = new CreatePaymentResponseModel(false, PaymentMapper.toDTO(payment), null);
            // Return response DTO
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            response = new CreatePaymentResponseModel(true, null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @Override
    public void payosTransferHandler(ObjectNode body) throws JsonProcessingException, IllegalArgumentException {
        String code = body.get("code").asText();
        String dataCode = body.has("data") && body.get("data").has("code") ? body.get("data").get("code").asText() : null;
        String dataDesc = body.has("data") && body.get("data").has("desc") ? body.get("data").get("desc").asText() : null;

        boolean success = "00".equals(code) || ("00".equals(dataCode) && "Thành công".equals(dataDesc));

        body.put("success", success);

        Webhook webhookBody = objectMapper.treeToValue(body, Webhook.class);
        try {
            WebhookData data = payOS.verifyPaymentWebhookData(webhookBody);
            PaymentEntity payment = paymentRepository.findByPaymentLinkId(data.getPaymentLinkId());
            PaymentStatus status = data.getCode().equals("00") ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
            if (status == PaymentStatus.SUCCESS) {
                payment.setStatus(PaymentStatus.SUCCESS);
//                cập nhật lại user sau khi mua gói ads xong
                // Retrieve the package that was purchased
                AdvertisementPackageEntity purchasedPackage = payment.getAdvertisementPackage();
                // Find and update the user who made the payment
                AppUserEntity appUser = payment.getUser();
                appUser.upgradePackage(purchasedPackage);  // Set new package, remaining ads, and expiry date

                // Save changes to user entity
                appUserRepository.save(appUser);
            }else{
                payment.setStatus(PaymentStatus.FAILED);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify payment webhook data: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<GetPaymentByIdResponseModel> getPaymentById(GetPaymentByIdRequestModel requestModel) {
        GetPaymentByIdResponseModel response;

        Optional<PaymentEntity> optionalPayment = this.paymentRepository.findById(requestModel.getPaymentId());
        if (optionalPayment.isEmpty()) {
            response = new GetPaymentByIdResponseModel(true, null, "Payment not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        PaymentEntity payment = optionalPayment.get();

        PaymentDTO paymentDTO = PaymentMapper.toDTO(payment);

        response = new GetPaymentByIdResponseModel(false, paymentDTO, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
