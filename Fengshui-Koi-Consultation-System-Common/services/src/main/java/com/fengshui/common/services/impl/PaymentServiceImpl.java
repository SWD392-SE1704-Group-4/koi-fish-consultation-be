package com.fengshui.common.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fengshui.common.services.PaymentService;
import com.fengshui.common.shared.Request.Payment.CreatePaymentRequestModel;
import com.fengshui.common.shared.Request.Payment.GetPaymentByIdRequestModel;
import com.fengshui.common.shared.Response.Payment.CreatePaymentResponseModel;
import com.fengshui.common.shared.Response.Payment.GetPaymentByIdResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.payos.PayOS;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("http://" + "${public.api.url}" + ":3000/")
    private String returnUrl;
    @Value("http://" + "${public.api.url}" + ":3000/")
    private String cancelUrl;

    @Autowired
    private PayOS payOS;

    @Override
    public ResponseEntity<CreatePaymentResponseModel> createPayment(CreatePaymentRequestModel requestModel) {
        return null;
    }

    @Override
    public void payosTransferHandler(ObjectNode body) throws JsonProcessingException, IllegalArgumentException {

    }

    @Override
    public ResponseEntity<GetPaymentByIdResponseModel> getPaymentById(GetPaymentByIdRequestModel requestModel) {
        return null;
    }
}
