package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.PaymentService;
import com.fengshui.common.services.TransactionService;
import com.fengshui.common.shared.Request.Payment.CreatePaymentRequestModel;
import com.fengshui.common.shared.Request.Transaction.CreateTransactionRequestModel;
import com.fengshui.common.shared.Response.Payment.CreatePaymentResponseModel;
import com.fengshui.common.shared.Response.Transaction.CreateTransactionResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payos-payment")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class PayOsPaymentResource {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(path = "/create", consumes = {"application/json"})
    public ResponseEntity<CreatePaymentResponseModel> createTransaction(@ModelAttribute CreatePaymentRequestModel requestModel) throws Exception {
        return this.paymentService.createPayment(requestModel);
    }

}
