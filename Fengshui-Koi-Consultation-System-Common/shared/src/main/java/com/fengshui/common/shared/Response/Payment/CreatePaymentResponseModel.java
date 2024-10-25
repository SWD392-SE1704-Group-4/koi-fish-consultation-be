package com.fengshui.common.shared.Response.Payment;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class CreatePaymentResponseModel extends BaseResponseModel {
    public CreatePaymentResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
