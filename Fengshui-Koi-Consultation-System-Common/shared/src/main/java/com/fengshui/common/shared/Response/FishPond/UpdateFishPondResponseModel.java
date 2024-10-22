package com.fengshui.common.shared.Response.FishPond;

import com.fengshui.common.repository.postgresql.dto.FishPondDTO;
import com.fengshui.common.repository.postgresql.dto.KoiFishDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;

public class UpdateFishPondResponseModel extends BaseResponseModel {
    public UpdateFishPondResponseModel(boolean has_error, FishPondDTO payload, Object error) {
        super(has_error, payload, error);
    }
}
