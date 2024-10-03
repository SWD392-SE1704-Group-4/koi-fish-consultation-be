package com.fengshui.common.shared.Response.KoiFish;

import com.fengshui.common.repository.postgresql.dto.KoiFishDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;

import java.util.List;

public class GetKoiFishResponseModel extends BaseResponseModel {
    public GetKoiFishResponseModel(boolean has_error, List<KoiFishDTO> payload, Object error) {
        super(has_error, payload, error);
    }
}
