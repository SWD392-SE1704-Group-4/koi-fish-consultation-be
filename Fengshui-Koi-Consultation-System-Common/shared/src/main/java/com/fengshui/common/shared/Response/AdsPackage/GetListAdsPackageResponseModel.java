package com.fengshui.common.shared.Response.AdsPackage;

import com.fengshui.common.repository.postgresql.dto.AdsPackageDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;
import java.util.List;

public class GetListAdsPackageResponseModel extends BaseResponseModel {
    public GetListAdsPackageResponseModel(boolean has_error, List<AdsPackageDTO> payload, Object error) {
        super(has_error, payload, error);
    }
}
