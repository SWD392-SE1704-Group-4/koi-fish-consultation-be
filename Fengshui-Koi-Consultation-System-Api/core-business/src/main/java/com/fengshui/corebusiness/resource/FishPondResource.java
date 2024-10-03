package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.FishPondService;
import com.fengshui.common.shared.Request.FishPond.CreateFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.GetFishPondRequestModel;
import com.fengshui.common.shared.Response.FishPond.CreateFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.GetFishPondResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fish-pond")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class FishPondResource {

    @Autowired
    private FishPondService fishPondService;


    @PostMapping(path = "/create", consumes = {"application/json"})
    public ResponseEntity<CreateFishPondResponseModel> createKoiFish(@RequestBody CreateFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.createFishPond(requestModel);
    }

    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<GetFishPondResponseModel> getKoiFish(@RequestBody GetFishPondRequestModel requestModel) throws Exception {
        return this.fishPondService.getListFishPond(requestModel);
    }
}
