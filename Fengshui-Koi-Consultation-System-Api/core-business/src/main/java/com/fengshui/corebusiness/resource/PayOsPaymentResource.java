package com.fengshui.corebusiness.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fengshui.common.services.PaymentService;
import com.fengshui.common.services.TransactionService;
import com.fengshui.common.shared.Request.Payment.CreatePaymentRequestModel;
import com.fengshui.common.shared.Request.Transaction.CreateTransactionRequestModel;
import com.fengshui.common.shared.Response.Payment.CreatePaymentResponseModel;
import com.fengshui.common.shared.Response.Transaction.CreateTransactionResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payos-payment")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class PayOsPaymentResource {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping(path = "/create", consumes = {"application/json"})
    public ResponseEntity<CreatePaymentResponseModel> createTransaction(@RequestBody CreatePaymentRequestModel requestModel) throws Exception {
        return this.paymentService.createPayment(requestModel);
    }
    @PostMapping(path = "/confirm-webhook")
    public ObjectNode payosTransferHandler(@RequestBody ObjectNode body)
    {
        ObjectNode response = objectMapper.createObjectNode();
        try {
            paymentService.payosTransferHandler(body);
            response.put("error", 0);
            response.put("message", "Webhook delivered");
            response.set("data", null);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", -1);
            response.put("message", e.getMessage());
            response.set("data", null);
            return response;
        }
    }
}
