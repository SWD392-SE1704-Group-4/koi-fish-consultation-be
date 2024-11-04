package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.FengshuiDirectionService;
import com.fengshui.common.shared.Request.Direction.GetFengshuiDirectionByElementRequestModel;
import com.fengshui.common.shared.Request.Direction.GetFengshuiDirectionRequestModel;
import com.fengshui.common.shared.Response.Direction.GetFengshuiDirectionByElementResponseModel;
import com.fengshui.common.shared.Response.Direction.GetFengshuiDirectionResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fengshui-direction")
@ComponentScan(basePackages = "com.fengshui.common.services")
public class FengshuiDirectionResource {

    @Autowired
    FengshuiDirectionService fengshuiDirectionService;

    @PostMapping(value = "/get-list", consumes = {"application/json"})
    public ResponseEntity<GetFengshuiDirectionResponseModel> getFengshuiElement(@RequestBody GetFengshuiDirectionRequestModel requestModel) throws Exception {
        return this.fengshuiDirectionService.getListFengshuiDirection(requestModel);
    }
    @PostMapping(value = "/get-list-by-element", consumes = {"application/json"})
    public ResponseEntity<GetFengshuiDirectionByElementResponseModel> getListFengshuiDirectionByElement(@RequestBody GetFengshuiDirectionByElementRequestModel requestModel){
        return this.fengshuiDirectionService.getListFengshuiDirectionByElement(requestModel);
    }
}
