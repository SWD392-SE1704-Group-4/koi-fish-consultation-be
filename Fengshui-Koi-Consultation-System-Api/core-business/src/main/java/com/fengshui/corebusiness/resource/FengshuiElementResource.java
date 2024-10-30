package com.fengshui.corebusiness.resource;

import com.fengshui.common.repository.postgresql.dto.FengshuiElementDTO;
import com.fengshui.common.services.FengshuiElementService;
import com.fengshui.common.services.KoiFishService;
import com.fengshui.common.shared.Request.Element.GetFengshuiElementRequestModel;
import com.fengshui.common.shared.Request.FishPond.GetFishPondRequestModel;
import com.fengshui.common.shared.Response.Element.GetFengshuiElementResponseModel;
import com.fengshui.common.shared.Response.FishPond.GetFishPondResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fengshui-element")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class FengshuiElementResource {

    @Autowired
    private FengshuiElementService fengshuiElementService;

    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<GetFengshuiElementResponseModel> getFengshuiElement(@RequestBody GetFengshuiElementRequestModel requestModel) throws Exception {
        return this.fengshuiElementService.getListFengshuiElement(requestModel);
    }

}
