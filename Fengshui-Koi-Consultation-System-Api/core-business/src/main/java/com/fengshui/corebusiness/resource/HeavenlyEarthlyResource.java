package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.FengshuiElementService;
import com.fengshui.common.services.HeavenlyEarthlyService;
import com.fengshui.common.shared.Request.Element.GetFengshuiElementRequestModel;
import com.fengshui.common.shared.Request.HeavenlyEarthlyModel.GetHeavenlyEarthlyRequestModel;
import com.fengshui.common.shared.Response.Element.GetFengshuiElementResponseModel;
import com.fengshui.common.shared.Response.HeavenlyEarthly.GetHeavenlyEarthlyResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/heavenly-earthly-elements")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class HeavenlyEarthlyResource {
    @Autowired
    private HeavenlyEarthlyService heavenlyEarthlyElementService;

    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<GetHeavenlyEarthlyResponseModel> getHeavenlyEarthlyElements(
            @RequestBody GetHeavenlyEarthlyRequestModel requestModel) throws Exception {
        return this.heavenlyEarthlyElementService.getListHeavenlyEarthlyElement(requestModel);
    }
}
