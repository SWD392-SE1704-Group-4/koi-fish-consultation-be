package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.AdvertisementService;
import com.fengshui.common.services.AppUserService;
import com.fengshui.common.shared.Request.Advertisement.GetListAdvertisementRequestModel;
import com.fengshui.common.shared.Request.AppUser.GetAppUserGroupRequestModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.AppUser.GetAppUserGroupResponseModel;
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

    @PreAuthorize("hasAnyRole('Member', 'Staff', 'Admin')")
    @PostMapping(value = "/get-user-group", consumes = {"application/json"})
    public ResponseEntity<GetAppUserGroupResponseModel> getAppUserGroup(@RequestBody GetAppUserGroupRequestModel requestModel) throws Exception {
        return this.appUserService.getAppUserGroup(requestModel);
    }

    @PostMapping(value = "/authorize", consumes = {"application/json"})
    public ResponseEntity<GetAppUserGroupResponseModel> authorizeUser(@RequestBody GetAppUserGroupRequestModel requestModel) throws Exception {
        return this.appUserService.getAppUserGroup(requestModel);
    }
}
