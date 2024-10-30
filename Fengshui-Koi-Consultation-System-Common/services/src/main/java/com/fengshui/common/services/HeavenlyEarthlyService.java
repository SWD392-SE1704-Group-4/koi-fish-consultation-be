package com.fengshui.common.services;

import com.fengshui.common.shared.Request.HeavenlyEarthlyModel.GetHeavenlyEarthlyRequestModel;
import com.fengshui.common.shared.Response.HeavenlyEarthly.GetHeavenlyEarthlyResponseModel;
import org.springframework.http.ResponseEntity;

public interface HeavenlyEarthlyService {
    ResponseEntity<GetHeavenlyEarthlyResponseModel> getListHeavenlyEarthlyElement(GetHeavenlyEarthlyRequestModel requestModel);
}
