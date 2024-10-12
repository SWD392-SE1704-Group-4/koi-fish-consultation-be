package com.fengshui.common.services;

import com.fengshui.common.shared.Request.Element.GetFengshuiElementRequestModel;
import com.fengshui.common.shared.Request.FishPond.GetFishPondRequestModel;
import com.fengshui.common.shared.Response.Element.GetFengshuiElementResponseModel;
import com.fengshui.common.shared.Response.FishPond.GetFishPondResponseModel;
import org.springframework.http.ResponseEntity;

public interface FengshuiElementService {
    public ResponseEntity<GetFengshuiElementResponseModel> getListFengshuiElement(GetFengshuiElementRequestModel requestModel);
}
