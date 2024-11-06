package com.fengshui.common.shared.Response.AppUser;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class GetAppUserResponseModel extends BaseResponseModel {
    public GetAppUserResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
