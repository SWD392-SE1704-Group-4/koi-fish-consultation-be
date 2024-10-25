package com.fengshui.common.services.impl;

import com.fengshui.common.services.AppUserService;
import com.fengshui.common.shared.Request.AppUser.GetAppUserGroupRequestModel;
import com.fengshui.common.shared.Response.AppUser.GetAppUserGroupResponseModel;
import org.springframework.http.ResponseEntity;

public class AppUserServiceImpl implements AppUserService {
    @Override
    public ResponseEntity<GetAppUserGroupResponseModel> getAppUserGroup(GetAppUserGroupRequestModel requestModel) {
        return null;
    }
}
