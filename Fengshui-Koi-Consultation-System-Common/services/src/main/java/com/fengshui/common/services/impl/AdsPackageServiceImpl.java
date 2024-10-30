package com.fengshui.common.services.impl;

import com.fengshui.common.aws.S3Client.S3Client;
import com.fengshui.common.repository.postgresql.IAdsPackageRepository;
import com.fengshui.common.repository.postgresql.dto.AdsPackageDTO;
import com.fengshui.common.repository.postgresql.mapper.AdsPackageMapper;
import com.fengshui.common.services.AdsPackageService;
import com.fengshui.common.shared.Request.AdsPackage.GetAdsPackageRequestModel;
import com.fengshui.common.shared.Response.AdsPackage.GetAdsPackageResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdsPackageServiceImpl implements AdsPackageService {
    @Autowired
    S3Client s3Client;

    @Autowired
    IAdsPackageRepository adsPackageRepository;
   // @Override
    public ResponseEntity<GetAdsPackageResponseModel> getAdsPackage(GetAdsPackageRequestModel requestModel) {
        GetAdsPackageResponseModel response;
        List<AdsPackageDTO> adsPackageList = this.adsPackageRepository.findAll().stream().map(AdsPackageMapper::toDTO).toList();
        response = new GetAdsPackageResponseModel(false, adsPackageList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
