package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.AdvertisementService;
import com.fengshui.common.services.KoiFishService;
import com.fengshui.common.shared.Request.Advertisement.*;
import com.fengshui.common.shared.Request.KoiFish.CreateKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.DeleteKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.GetKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.UpdateKoiFishRequestModel;
import com.fengshui.common.shared.Response.Advertisement.*;
import com.fengshui.common.shared.Response.KoiFish.CreateKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.DeleteKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.GetKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.UpdateKoiFishResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/advertisement")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class AdvertisementResource {

    @Autowired
    private AdvertisementService advertisementService;

    @PreAuthorize("hasRole('Member')")
    @PostMapping(path = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<CreateAdvertisementResponseModel> createAdvertisement(@ModelAttribute CreateAdvertisementRequestModel requestModel) throws Exception {
        return this.advertisementService.createAdvertisement(requestModel);
    }
    @PostMapping(value = "/get-by-id", consumes = {"application/json"})
    public ResponseEntity<GetAdvertisementByIdResponseModel> getAdvertisementById(@RequestBody GetAdvertisementByIdRequestModel requestModel) throws Exception {
        return this.advertisementService.getAdvertisementById(requestModel);
    }
    @PreAuthorize("hasRole('Member')")
    @PostMapping(value = "/get-list-by-creator", consumes = {"application/json"})
    public ResponseEntity<GetListAdvertisementByCreatorResponseModel> getListAdvertisement(@RequestBody GetListAdvertisementByCreatorRequestModel requestModel) throws Exception {
        return this.advertisementService.getListAdvertisementByCreator(requestModel);
    }
    @PreAuthorize("hasAnyRole('Member', 'Staff', 'Admin')")
    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<GetListAdvertisementResponseModel> getListAdvertisement(@RequestBody GetListAdvertisementRequestModel requestModel) throws Exception {
        return this.advertisementService.getListAdvertisement(requestModel);
    }
    @PreAuthorize("hasAnyRole('Member', 'Staff', 'Admin')")
    @PostMapping(value = "/get-list-advertisement-type", consumes = {"application/json"})
    public ResponseEntity<GetListAdvertisementResponseModel> getListAdvertisementType(@RequestBody GetListAdvertisementRequestModel requestModel) throws Exception {
        return this.advertisementService.getListAdvertisementType(requestModel);
    }
    @PreAuthorize("hasAnyRole('Member')")
    @PostMapping(value = "/update", consumes = {"multipart/form-data"})
    public ResponseEntity<UpdateAdvertisementResponseModel> updateAdvertisement(@ModelAttribute UpdateAdvertisementRequestModel requestModel) throws Exception {
        return this.advertisementService.updateAdvertisement(requestModel);
    }
    @PreAuthorize("hasAnyRole('Staff')")
    @PostMapping(value = "/delete", consumes = {"application/json"})
    public ResponseEntity<DeleteAdvertisementResponseModel> deleteAdvertisement(@RequestBody DeleteAdvertisementRequestModel requestModel) throws Exception {
        return this.advertisementService.deleteAdvertisement(requestModel);
    }
    @PreAuthorize("hasAnyRole('Staff')")
    @PostMapping("/approve")
    public ResponseEntity<VerifyAdvertisementResponseModel> approveAdvertisement(@RequestBody VerifyAdvertisementRequestModel requestModel) {
        return this.advertisementService.verifyAdvertisement(requestModel);
    }
    @PreAuthorize("hasAnyRole('Staff')")
    @PostMapping("/deny")
    public ResponseEntity<DenyAdvertisementResponseModel> denyAdvertisement(@RequestBody DenyAdvertisementRequestModel requestModel) {
        return this.advertisementService.denyAdvertisement(requestModel);
    }
}
