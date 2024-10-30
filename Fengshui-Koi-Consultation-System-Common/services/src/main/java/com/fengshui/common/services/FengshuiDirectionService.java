package com.fengshui.common.services;

import com.fengshui.common.shared.Request.Direction.GetFengshuiDirectionRequestModel;
import com.fengshui.common.shared.Request.Element.GetFengshuiElementRequestModel;
import com.fengshui.common.shared.Response.Direction.GetFengshuiDirectionResponseModel;
import com.fengshui.common.shared.Response.Element.GetFengshuiElementResponseModel;
import org.springframework.http.ResponseEntity;

public interface FengshuiDirectionService {
    public ResponseEntity<GetFengshuiDirectionResponseModel> getListFengshuiDirection(GetFengshuiDirectionRequestModel requestModel);
}
