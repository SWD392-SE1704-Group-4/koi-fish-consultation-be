package com.fengshui.common.shared.Response.Payment;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class GetPaymentByIdResponseModel extends BaseResponseModel {
    public GetPaymentByIdResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
