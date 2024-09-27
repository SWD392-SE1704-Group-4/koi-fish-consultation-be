package com.fengshui.common.shared.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseResponseModel {
    private boolean has_error;
    private Object payload;
    private Object error;

    public BaseResponseModel(boolean has_error, Object payload, Object error) {
        this.has_error = has_error;
        this.payload = payload;
        this.error = error;
    }
}
