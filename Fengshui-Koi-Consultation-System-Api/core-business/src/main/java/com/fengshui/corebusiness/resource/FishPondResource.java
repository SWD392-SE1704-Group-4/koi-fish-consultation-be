package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.FishPondService;
import com.fengshui.common.shared.Request.FishPond.CreateFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.DeleteFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.GetFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.UpdateFishPondRequestModel;
import com.fengshui.common.shared.Request.KoiFish.DeleteKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.UpdateKoiFishRequestModel;
import com.fengshui.common.shared.Response.FishPond.CreateFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.DeleteFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.GetFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.UpdateFishPondResponseModel;
import com.fengshui.common.shared.Response.KoiFish.DeleteKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.UpdateKoiFishResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fish-pond")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class FishPondResource {

    @Autowired
    private FishPondService fishPondService;


    @PostMapping(path = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<CreateFishPondResponseModel> createKoiFish(@ModelAttribute CreateFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.createFishPond(requestModel);
    }

    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<GetFishPondResponseModel> getKoiFish(@RequestBody GetFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.getListFishPond(requestModel);
    }

    @PostMapping(value = "/update", consumes = {"multipart/form-data"})
    public ResponseEntity<UpdateFishPondResponseModel> updateFishPond(@ModelAttribute UpdateFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.updateFishPond(requestModel);
    }

    @PostMapping(value = "/delete", consumes = {"application/json"})
    public ResponseEntity<DeleteFishPondResponseModel> deleteKoiFish(@RequestBody DeleteFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.deleteFishPond(requestModel);
    }
}
