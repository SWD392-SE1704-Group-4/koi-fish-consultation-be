package com.fengshui.common.shared.Response.Advertisement;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class VerifyAdvertisementResponseModel extends BaseResponseModel {
    public VerifyAdvertisementResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
