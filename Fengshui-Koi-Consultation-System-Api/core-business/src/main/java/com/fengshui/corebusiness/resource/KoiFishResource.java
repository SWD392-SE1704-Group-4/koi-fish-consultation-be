package com.fengshui.corebusiness.resource;

import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import com.fengshui.common.services.KoiFishService;
import com.fengshui.common.services.impl.KoiFishServiceImpl;
import com.fengshui.common.shared.Request.KoiFish.CreateKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.GetKoiFishRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/koi-fish")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class KoiFishResource {

    @Autowired
    private KoiFishService koiFishService;


    @PostMapping(path = "/create", consumes = {"application/json"})
    public ResponseEntity<Object> createKoiFish(@RequestBody CreateKoiFishRequestModel requestModel) throws Exception {
        return ResponseEntity.ok(this.koiFishService.createKoiFish(requestModel));
    }

    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<Object> getKoiFish(@RequestBody GetKoiFishRequestModel requestModel) throws Exception {
        return ResponseEntity.ok(this.koiFishService.getListKoiFish(requestModel));
    }

//    @PostMapping(value = "/create", consumes = {"application/json"})
//    public ResponseEntity<Object> updateKoiFish(UpdateKoiFishRequest requestModel) throws Exception {
//        return null;
//    }
//
//    @PostMapping(value = "/create", consumes = {"application/json"})
//    public ResponseEntity<Object> deleteKoiFish(DeleteKoiFishRequest requestModel) throws Exception {
//        return null;
//    }
}
