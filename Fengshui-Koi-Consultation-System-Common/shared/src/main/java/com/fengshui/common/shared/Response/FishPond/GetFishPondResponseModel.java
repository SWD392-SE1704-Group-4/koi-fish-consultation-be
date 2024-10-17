package com.fengshui.common.shared.Response.FishPond;

import com.fengshui.common.repository.postgresql.dto.FishPondDTO;
import com.fengshui.common.repository.postgresql.dto.KoiFishDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;

import java.util.List;

public class GetFishPondResponseModel extends BaseResponseModel {
    public GetFishPondResponseModel(boolean has_error, List<FishPondDTO> payload, Object error) {
        super(has_error, payload, error);
    }
}
