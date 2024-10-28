package com.fengshui.common.shared.Response.AdsPackage;

import com.fengshui.common.repository.postgresql.dto.AdsPackageDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;

public class UpdateAdsPackageResponseModel extends BaseResponseModel {
    public UpdateAdsPackageResponseModel(boolean has_error, AdsPackageDTO payload, Object error) {
        super(has_error, payload, error);
    }
}
