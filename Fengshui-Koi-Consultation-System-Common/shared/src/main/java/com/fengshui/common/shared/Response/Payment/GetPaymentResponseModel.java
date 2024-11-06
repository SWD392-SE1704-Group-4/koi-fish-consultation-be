package com.fengshui.common.shared.Response.Payment;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class GetPaymentResponseModel extends BaseResponseModel {
    public GetPaymentResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
