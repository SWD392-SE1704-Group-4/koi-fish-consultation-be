package com.fengshui.common.services;

import com.fengshui.common.shared.Request.AdsPackage.GetAdsPackageRequestModel;
import com.fengshui.common.shared.Response.AdsPackage.GetAdsPackageResponseModel;
import org.springframework.http.ResponseEntity;

public interface AdsPackageService {
    public ResponseEntity<GetAdsPackageResponseModel> getAdsPackage(GetAdsPackageRequestModel requestModel);
}
