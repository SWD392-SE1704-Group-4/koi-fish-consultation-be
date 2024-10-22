package com.fengshui.common.services;

import com.fengshui.common.shared.Request.Advertisement.GetListAdvertisementRequestModel;
import com.fengshui.common.shared.Request.AppUser.GetAppUserGroupRequestModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.AppUser.GetAppUserGroupResponseModel;
import org.springframework.http.ResponseEntity;

public interface AppUserService {
    public ResponseEntity<GetAppUserGroupResponseModel> getAppUserGroup(GetAppUserGroupRequestModel requestModel);
}
