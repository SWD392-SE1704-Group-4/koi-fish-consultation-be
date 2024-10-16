package com.fengshui.common.shared.Response.KoiFish;

import com.fengshui.common.repository.postgresql.dto.KoiFishDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;

public class UpdateKoiFishResponseModel extends BaseResponseModel {
    public UpdateKoiFishResponseModel(boolean has_error, KoiFishDTO payload, Object error) {
        super(has_error, payload, error);
    }
}
