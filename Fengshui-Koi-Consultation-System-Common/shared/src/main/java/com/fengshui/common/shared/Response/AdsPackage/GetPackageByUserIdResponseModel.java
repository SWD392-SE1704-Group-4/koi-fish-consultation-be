package com.fengshui.common.shared.Response.AdsPackage;

import com.fengshui.common.repository.postgresql.dto.AdsPackageDTO;
import com.fengshui.common.repository.postgresql.dto.AdvertisementPackageDTO;
import com.fengshui.common.repository.postgresql.mapper.AdvertisementPackageMapper;
import com.fengshui.common.shared.Response.BaseResponseModel;

public class GetPackageByUserIdResponseModel extends BaseResponseModel {
    public GetPackageByUserIdResponseModel(boolean has_error, AdvertisementPackageDTO payload, Object error) {
        super(has_error, payload, error);
    }
}
