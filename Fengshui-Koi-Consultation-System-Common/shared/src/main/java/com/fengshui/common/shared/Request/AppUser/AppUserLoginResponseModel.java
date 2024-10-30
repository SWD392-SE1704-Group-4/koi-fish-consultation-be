package com.fengshui.common.shared.Request.AppUser;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class AppUserLoginResponseModel extends BaseResponseModel {
    public AppUserLoginResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
