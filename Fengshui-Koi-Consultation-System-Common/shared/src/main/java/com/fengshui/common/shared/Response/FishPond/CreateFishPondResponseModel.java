package com.fengshui.common.shared.Response.FishPond;

import com.fengshui.common.repository.postgresql.dto.FishPondDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CreateFishPondResponseModel extends BaseResponseModel {
    public CreateFishPondResponseModel(boolean has_error, FishPondDTO payload, Object error) {
        super(has_error, payload, error);
    }
}
