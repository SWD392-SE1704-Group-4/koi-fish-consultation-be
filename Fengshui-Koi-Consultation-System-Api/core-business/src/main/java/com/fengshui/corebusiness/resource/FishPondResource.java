package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.FishPondService;

import com.fengshui.common.shared.Request.FishPond.*;
import com.fengshui.common.shared.Request.KoiFish.DeleteKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.UpdateKoiFishRequestModel;
import com.fengshui.common.shared.Response.FishPond.*;
import com.fengshui.common.shared.Response.KoiFish.DeleteKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.UpdateKoiFishResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fish-pond")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class FishPondResource {

    @Autowired
    private FishPondService fishPondService;

    @PreAuthorize("hasRole('Member')")
    @PostMapping(path = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<CreateFishPondResponseModel> createFishPond(@ModelAttribute CreateFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.createFishPond(requestModel);
    }
    @PreAuthorize("hasRole('Member')")
    @PostMapping(value = "/get-list-by-creator", consumes = {"application/json"})
    public ResponseEntity<GetFishPondByUserResponseModel> getFishPondByCreator(@RequestBody GetFishPondByUserRequestModel requestModel) throws Exception {
        return this.fishPondService.getListFishPondByCreator(requestModel);
    }

    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<GetFishPondResponseModel> getFishPond(@RequestBody GetFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.getListFishPond(requestModel);
    }

    @PreAuthorize("hasRole('Member')")
    @PostMapping(value = "/update", consumes = {"multipart/form-data"})
    public ResponseEntity<UpdateFishPondResponseModel> updateFishPond(@ModelAttribute UpdateFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.updateFishPond(requestModel);
    }

    @PostMapping(value = "/delete", consumes = {"application/json"})
    public ResponseEntity<DeleteFishPondResponseModel> deleteKoiFish(@RequestBody DeleteFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.deleteFishPond(requestModel);
    }

    @PreAuthorize("hasRole('Member')")
    @PostMapping(value = "/delete", consumes = {"application/json"})
    public ResponseEntity<DeleteFishPondResponseModel> deleteFishPond(@RequestBody DeleteFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.deleteFishPond(requestModel);
    }
}
