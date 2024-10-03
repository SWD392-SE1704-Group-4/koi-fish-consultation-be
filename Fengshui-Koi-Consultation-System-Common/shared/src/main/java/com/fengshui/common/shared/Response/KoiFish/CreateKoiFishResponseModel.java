package com.fengshui.common.shared.Response.KoiFish;

import com.fengshui.common.repository.postgresql.dto.KoiFishDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CreateKoiFishResponseModel extends BaseResponseModel {
    public CreateKoiFishResponseModel(boolean has_error, KoiFishDTO payload, Object error) {
        super(has_error, payload, error);
    }
}
