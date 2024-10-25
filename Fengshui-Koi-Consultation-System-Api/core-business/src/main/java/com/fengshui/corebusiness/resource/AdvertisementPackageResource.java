package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.AdvertisementPackageService;
import com.fengshui.common.shared.Request.Advertisement.CreateAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.GetListAdvertisementRequestModel;
import com.fengshui.common.shared.Request.AdvertisementPackage.CreateAdvertisementPackageRequestModel;
import com.fengshui.common.shared.Request.AdvertisementPackage.GetListAdvertisementPackageRequestModel;
import com.fengshui.common.shared.Response.Advertisement.CreateAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.CreateAdvertisementPackageResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.GetListAdvertisementPackageResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/advertisement-package")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class AdvertisementPackageResource {

    @Autowired
    private AdvertisementPackageService advertisementPackageService;

    @PostMapping(path = "/create", consumes = {"application/json"})
    public ResponseEntity<CreateAdvertisementPackageResponseModel> createAdvertisementPackage(@ModelAttribute CreateAdvertisementPackageRequestModel requestModel) throws Exception {
        return this.advertisementPackageService.createAdvertisementPackage(requestModel);
    }

//    @PostMapping(value = "/get-list", consumes = {"application/json"})
//    public ResponseEntity<GetListAdvertisementPackageResponseModel> getListAdvertisementPackage(@RequestBody GetListAdvertisementPackageRequestModel requestModel) throws Exception {
//        return this.advertisementPackageService.getListAdvertisementPackage(requestModel);
//    }
}
