package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.PersonalFengshuiService;
import com.fengshui.common.shared.Request.Advertisement.CreateAdvertisementRequestModel;
import com.fengshui.common.shared.Request.PersonalFengshui.CreatePersonalFengshuiRequestModel;
import com.fengshui.common.shared.Request.PersonalFengshui.GetPersonalFengshuiResponseModel;
import com.fengshui.common.shared.Response.Advertisement.CreateAdvertisementResponseModel;
import com.fengshui.common.shared.Response.PersonalFengshui.CreatePersonalFengshuiResponseModel;
import com.fengshui.common.shared.Response.PersonalFengshui.GetPersonalFengshuiRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/personal-fengshui")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class PersonalFengshuiResouce {
    @Autowired
    private PersonalFengshuiService personalFengshuiService;
    @PostMapping(path = "/save", consumes = {"application/json"})
    public ResponseEntity<CreatePersonalFengshuiResponseModel> savePersonalFengshui(@RequestBody CreatePersonalFengshuiRequestModel requestModel){
        return this.personalFengshuiService.createPersonalFengshui(requestModel);
    };

    @PostMapping(path = "/get-by-user-id", consumes = {"application/json"})
    public ResponseEntity<GetPersonalFengshuiResponseModel> getPersonalFengshui(@RequestBody GetPersonalFengshuiRequestModel requestModel){
        return this.personalFengshuiService.getPersonalFengshui(requestModel);
    };
}
