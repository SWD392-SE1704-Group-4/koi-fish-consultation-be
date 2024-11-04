package com.fengshui.common.services;

import com.fengshui.common.shared.Request.AdsPackage.GetPackageByUserIdRequestModel;
import com.fengshui.common.shared.Request.Advertisement.GetListAdvertisementRequestModel;
import com.fengshui.common.shared.Request.AppUser.AppUserLoginResponseModel;
import com.fengshui.common.shared.Request.AppUser.GetAppUserByIdRequestModel;
import com.fengshui.common.shared.Request.AppUser.GetAppUserGroupRequestModel;
import com.fengshui.common.shared.Request.AppUser.GetAppUserRoleRequestModel;
import com.fengshui.common.shared.Response.AdsPackage.GetPackageByUserIdResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.AppUser.AppUserLoginRequestModel;
import com.fengshui.common.shared.Response.AppUser.GetAppUserByIdResponseModel;
import com.fengshui.common.shared.Response.AppUser.GetAppUserGroupResponseModel;
import com.fengshui.common.shared.Response.AppUser.GetAppUserRoleResponseModel;
import org.springframework.http.ResponseEntity;

public interface AppUserService {
    public ResponseEntity<GetAppUserGroupResponseModel> getAppUserGroup(GetAppUserGroupRequestModel requestModel);
    public ResponseEntity<GetAppUserRoleResponseModel> getAppUserRole(GetAppUserRoleRequestModel requestModel);
    public ResponseEntity<GetAppUserByIdResponseModel> getAppUserById(GetAppUserByIdRequestModel requestModel);
    public ResponseEntity<GetPackageByUserIdResponseModel> getAppUserPackage(GetPackageByUserIdRequestModel requestModel);
    public ResponseEntity<Object> changePassword(Object requestModel);
    public ResponseEntity<Object> verifyEmail(Object requestModel);
    public ResponseEntity<AppUserLoginResponseModel> loginAppUser(AppUserLoginRequestModel requestModel);
}
