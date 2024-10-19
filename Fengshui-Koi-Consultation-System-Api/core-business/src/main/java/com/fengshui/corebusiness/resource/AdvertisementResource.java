package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.AdvertisementService;
import com.fengshui.common.services.KoiFishService;
import com.fengshui.common.shared.Request.Advertisement.CreateAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.DeleteAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.GetListAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.UpdateAdvertisementRequestModel;
import com.fengshui.common.shared.Request.KoiFish.CreateKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.DeleteKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.GetKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.UpdateKoiFishRequestModel;
import com.fengshui.common.shared.Response.Advertisement.CreateAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.DeleteAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.UpdateAdvertisementResponseModel;
import com.fengshui.common.shared.Response.KoiFish.CreateKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.DeleteKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.GetKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.UpdateKoiFishResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/advertisement")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class AdvertisementResource {

    @Autowired
    private AdvertisementService advertisementResource;


    @PostMapping(path = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<CreateAdvertisementResponseModel> createAdvertisement(@ModelAttribute CreateAdvertisementRequestModel requestModel) throws Exception {
        return this.advertisementResource.createAdvertisement(requestModel);
    }

    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<GetListAdvertisementResponseModel> getKoiFish(@RequestBody GetListAdvertisementRequestModel requestModel) throws Exception {
        return this.advertisementResource.getListAdvertisement(requestModel);
    }

    @PostMapping(value = "/update", consumes = {"multipart/form-data"})
    public ResponseEntity<UpdateAdvertisementResponseModel> updateKoiFish(@ModelAttribute UpdateAdvertisementRequestModel requestModel) throws Exception {
        return this.advertisementResource.updateAdvertisement(requestModel);
    }

    @PostMapping(value = "/delete", consumes = {"application/json"})
    public ResponseEntity<DeleteAdvertisementResponseModel> deleteKoiFish(@RequestBody DeleteAdvertisementRequestModel requestModel) throws Exception {
        return this.advertisementResource.deleteAdvertisement(requestModel);
    }
}
