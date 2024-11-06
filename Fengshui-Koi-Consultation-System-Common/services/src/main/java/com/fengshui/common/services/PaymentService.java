package com.fengshui.common.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fengshui.common.shared.Request.Payment.CreatePaymentRequestModel;
import com.fengshui.common.shared.Request.Payment.GetPaymentByIdRequestModel;
import com.fengshui.common.shared.Request.Payment.GetPaymentRequestModel;
import com.fengshui.common.shared.Response.Payment.CreatePaymentResponseModel;
import com.fengshui.common.shared.Response.Payment.GetPaymentByIdResponseModel;
import com.fengshui.common.shared.Response.Payment.GetPaymentResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentService {
    public ResponseEntity<CreatePaymentResponseModel> createPayment(CreatePaymentRequestModel requestModel);
    void payosTransferHandler(ObjectNode body) throws JsonProcessingException, IllegalArgumentException;
    public ResponseEntity<GetPaymentByIdResponseModel> getPaymentById(GetPaymentByIdRequestModel requestModel);
    public ResponseEntity<GetPaymentResponseModel> getPayment(GetPaymentRequestModel requestModel);
}
