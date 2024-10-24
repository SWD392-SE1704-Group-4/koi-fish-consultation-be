package com.fengshui.common.shared.Response.Advertisement;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class DeleteAdvertisementResponseModel extends BaseResponseModel {
    public DeleteAdvertisementResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
