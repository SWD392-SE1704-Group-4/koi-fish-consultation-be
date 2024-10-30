package com.fengshui.common.shared.Response.AppUser;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class GetAppUserByIdResponseModel extends BaseResponseModel {
    public GetAppUserByIdResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
