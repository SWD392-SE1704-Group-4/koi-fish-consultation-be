package com.fengshui.common.services;

import com.fengshui.common.shared.Request.AdsPackage.GetPackageByUserIdRequestModel;
import com.fengshui.common.shared.Request.Advertisement.GetListAdvertisementRequestModel;
import com.fengshui.common.shared.Request.AppUser.*;
import com.fengshui.common.shared.Response.AdsPackage.GetPackageByUserIdResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.AppUser.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AppUserService {
    public ResponseEntity<GetAppUserGroupResponseModel> getAppUserGroup(GetAppUserGroupRequestModel requestModel);
    public ResponseEntity<GetAppUserRoleResponseModel> getAppUserRole(GetAppUserRoleRequestModel requestModel);
    public ResponseEntity<GetAppUserByIdResponseModel> getAppUserById(GetAppUserByIdRequestModel requestModel);
    public ResponseEntity<GetPackageByUserIdResponseModel> getAppUserPackage(GetPackageByUserIdRequestModel requestModel);
    public ResponseEntity<Object> changePassword(Object requestModel);
    public ResponseEntity<Object> verifyEmail(Object requestModel);
    public ResponseEntity<AppUserLoginResponseModel> loginAppUser(AppUserLoginRequestModel requestModel);
    public ResponseEntity<GetAppUserResponseModel> getAppUser(GetAppUserRequestModel requestModel);
}
