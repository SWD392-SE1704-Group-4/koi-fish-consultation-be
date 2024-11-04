package com.fengshui.corebusiness.resource;

import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import com.fengshui.common.services.KoiFishService;
import com.fengshui.common.services.impl.KoiFishServiceImpl;
import com.fengshui.common.shared.Request.KoiFish.*;
import com.fengshui.common.shared.Response.KoiFish.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/koi-fish")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class KoiFishResource {

    @Autowired
    private KoiFishService koiFishService;

    @PreAuthorize("hasAnyAuthority('STAFF')")
    @PostMapping(path = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<CreateKoiFishResponseModel> createKoiFish(@ModelAttribute CreateKoiFishRequestModel requestModel) throws Exception {
        return this.koiFishService.createKoiFish(requestModel);
    }

    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<GetKoiFishResponseModel> getKoiFish(@RequestBody GetKoiFishRequestModel requestModel) throws Exception {
        return this.koiFishService.getListKoiFish(requestModel);
    }

    @PostMapping(value = "/get-list-by-element-name", consumes = {"application/json"})
    public ResponseEntity<GetKoiFishByElementNameResponseModel> getKoiFish(@RequestBody GetKoiFishByElementNameRequestModel requestModel) throws Exception {
        return this.koiFishService.getListKoiFishByElementName(requestModel);
    }

    @PreAuthorize("hasAnyAuthority('STAFF')")
    @PostMapping(value = "/update", consumes = {"multipart/form-data"})
    public ResponseEntity<UpdateKoiFishResponseModel> updateKoiFish(@ModelAttribute UpdateKoiFishRequestModel requestModel) throws Exception {
        return this.koiFishService.updateKoiFish(requestModel);
    }

    @PreAuthorize("hasAnyAuthority('STAFF')")
    @PostMapping(value = "/delete", consumes = {"application/json"})
    public ResponseEntity<DeleteKoiFishResponseModel> deleteKoiFish(@RequestBody DeleteKoiFishRequestModel requestModel) throws Exception {
        return this.koiFishService.deleteKoiFish(requestModel);
    }
}
