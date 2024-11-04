package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.AdvertisementService;
import com.fengshui.common.services.AppUserService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/app-user")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class AppUserResource {

    @Autowired
    private AppUserService appUserService;

    @PostMapping(value = "/get-user-group", consumes = {"application/json"})
    public ResponseEntity<GetAppUserGroupResponseModel> getAppUserGroup(@RequestBody GetAppUserGroupRequestModel requestModel) throws Exception {
        return this.appUserService.getAppUserGroup(requestModel);
    }
    @PostMapping(value = "/get-user-role", consumes = {"application/json"})
    public ResponseEntity<GetAppUserRoleResponseModel> getAppUserRole(@RequestBody GetAppUserRoleRequestModel requestModel) throws Exception {
        return this.appUserService.getAppUserRole(requestModel);
    }

    @PostMapping(value = "/get-user-package", consumes = {"application/json"})
    public ResponseEntity<GetPackageByUserIdResponseModel> getPackageByUserId(@RequestBody GetPackageByUserIdRequestModel requestModel) throws Exception {
        return this.appUserService.getAppUserPackage(requestModel);
    }

    @PostMapping(value = "/authorize", consumes = {"application/json"})
    public ResponseEntity<GetAppUserGroupResponseModel> authorizeUser(@RequestBody GetAppUserGroupRequestModel requestModel) throws Exception {
        return this.appUserService.getAppUserGroup(requestModel);
    }

    @PostMapping(value = "/get-by-id", consumes = {"application/json"})
    public ResponseEntity<GetAppUserByIdResponseModel> authorizeUser(@RequestBody GetAppUserByIdRequestModel requestModel) throws Exception {
        return this.appUserService.getAppUserById(requestModel);
    }

    @PostMapping(value = "/login", consumes = {"application/json"})
    public ResponseEntity<AppUserLoginResponseModel> login(@RequestBody AppUserLoginRequestModel requestModel) throws Exception {
        return this.appUserService.loginAppUser(requestModel);
    }
}
