package com.fengshui.common.shared.Response.AppUser;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class GetAppUserRoleResponseModel extends BaseResponseModel {
    public GetAppUserRoleResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
