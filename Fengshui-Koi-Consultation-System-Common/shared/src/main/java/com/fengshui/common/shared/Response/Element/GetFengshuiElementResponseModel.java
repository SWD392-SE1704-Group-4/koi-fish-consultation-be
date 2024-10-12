package com.fengshui.common.shared.Response.Element;

import com.fengshui.common.repository.postgresql.dto.FengshuiElementDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;

import java.util.List;

public class GetFengshuiElementResponseModel extends BaseResponseModel {
    public GetFengshuiElementResponseModel(boolean has_error, List<FengshuiElementDTO> payload, Object error) {
        super(has_error, payload, error);
    }
}
