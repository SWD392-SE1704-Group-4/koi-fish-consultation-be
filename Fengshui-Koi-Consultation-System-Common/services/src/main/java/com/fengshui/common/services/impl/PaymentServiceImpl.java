package com.fengshui.common.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fengshui.common.repository.postgresql.IAppUserRepository;
import com.fengshui.common.repository.postgresql.IPaymentRepository;
import com.fengshui.common.repository.postgresql.entities.AppUserEntity;
import com.fengshui.common.repository.postgresql.entities.PaymentEntity;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("http://" + "${public.api.url}" + ":3000/")
    private String returnUrl;
    @Value("http://" + "${public.api.url}" + ":3000/")
    private String cancelUrl;

    @Autowired
    private PayOS payOS;

    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private IPaymentRepository paymentRepository;

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

    }

    @Override
    public ResponseEntity<GetPaymentByIdResponseModel> getPaymentById(GetPaymentByIdRequestModel requestModel) {
        return null;
    }
}
