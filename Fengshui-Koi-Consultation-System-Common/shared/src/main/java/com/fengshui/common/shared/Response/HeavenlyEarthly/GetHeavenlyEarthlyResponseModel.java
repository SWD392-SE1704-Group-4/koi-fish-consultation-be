package com.fengshui.common.shared.Response.HeavenlyEarthly;

import com.fengshui.common.repository.postgresql.dto.HeavenlyEarthlyDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;

import java.util.List;

public class GetHeavenlyEarthlyResponseModel extends BaseResponseModel {
    public GetHeavenlyEarthlyResponseModel(boolean hasError, List<HeavenlyEarthlyDTO> payload, Object error) {
        super(hasError, payload, error);
    }
}
