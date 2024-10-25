package com.fengshui.common.shared.Response.Direction;

import com.fengshui.common.shared.Response.BaseResponseModel;

public class GetFengshuiDirectionResponseModel extends BaseResponseModel {
    public GetFengshuiDirectionResponseModel(boolean has_error, Object payload, Object error) {
        super(has_error, payload, error);
    }
}
