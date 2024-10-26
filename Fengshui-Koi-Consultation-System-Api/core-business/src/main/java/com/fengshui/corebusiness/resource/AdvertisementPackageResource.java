package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.AdvertisementPackageService;
import com.fengshui.common.shared.Request.Advertisement.*;
import com.fengshui.common.shared.Request.AdvertisementPackage.*;
import com.fengshui.common.shared.Response.Advertisement.*;
import com.fengshui.common.shared.Response.AdvertisementPackage.*;
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

    @PostMapping(value = "/delete", consumes = {"application/json"})
    public ResponseEntity<DeleteAdvertisementPackageResponseModel> deleteAdvertisementPackage(@RequestBody DeleteAdvertisementPackageRequestModel requestModel) throws Exception {
        return this.advertisementPackageService.deleteAdvertisementPackage(requestModel);
    }
}
