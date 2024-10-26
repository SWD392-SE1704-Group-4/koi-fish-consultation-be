package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.AdvertisementPackageService;
import com.fengshui.common.shared.Request.Advertisement.CreateAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.GetAdvertisementByIdRequestModel;
import com.fengshui.common.shared.Request.Advertisement.GetListAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.UpdateAdvertisementRequestModel;
import com.fengshui.common.shared.Request.AdvertisementPackage.CreateAdvertisementPackageRequestModel;
import com.fengshui.common.shared.Request.AdvertisementPackage.GetAdvertisementPackageByIdRequestModel;
import com.fengshui.common.shared.Request.AdvertisementPackage.GetListAdvertisementPackageRequestModel;
import com.fengshui.common.shared.Request.AdvertisementPackage.UpdateAdvertisementPackageRequestModel;
import com.fengshui.common.shared.Response.Advertisement.CreateAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetAdvertisementByIdResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.UpdateAdvertisementResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.CreateAdvertisementPackageResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.GetAdvertisementPackageByIdResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.GetListAdvertisementPackageResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.UpdateAdvertisementPackageResponseModel;
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

    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<GetListAdvertisementPackageResponseModel> getListAdvertisementPackage(@RequestBody GetListAdvertisementPackageRequestModel requestModel) throws Exception {
        return this.advertisementPackageService.getListAdvertisementPackage(requestModel);
    }

    @PostMapping(value = "/get-by-id", consumes = {"application/json"})
    public ResponseEntity<GetAdvertisementPackageByIdResponseModel> getAdvertisementPackageById(@RequestBody GetAdvertisementPackageByIdRequestModel requestModel) throws Exception {
        return this.advertisementPackageService.getAdvertisementPackageById(requestModel);
    }

    @PostMapping(value = "/update", consumes = {"multipart/form-data"})
    public ResponseEntity<UpdateAdvertisementPackageResponseModel> updateAdvertisementPackage(@ModelAttribute UpdateAdvertisementPackageRequestModel requestModel) throws Exception {
        return this.advertisementPackageService.updateAdvertisementPackage(requestModel);
    }
}
