package com.fengshui.common.shared.Response.Advertisement;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class DenyAdvertisementResponseModel extends BaseResponseModel {
    public DenyAdvertisementResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
