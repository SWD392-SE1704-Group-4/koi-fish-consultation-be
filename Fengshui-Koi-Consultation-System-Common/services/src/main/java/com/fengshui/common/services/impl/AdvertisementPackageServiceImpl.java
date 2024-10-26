package com.fengshui.common.services.impl;

import com.fengshui.common.aws.Cognito.CognitoUserPool;
import com.fengshui.common.repository.postgresql.IAdvertisementPackageRepository;
import com.fengshui.common.repository.postgresql.dto.AdvertisementPackageDTO;
import com.fengshui.common.repository.postgresql.entities.AdvertisementEntity;
import com.fengshui.common.repository.postgresql.entities.AdvertisementPackageEntity;
import com.fengshui.common.repository.postgresql.mapper.AdvertisementMapper;
import com.fengshui.common.repository.postgresql.mapper.AdvertisementPackageMapper;
import com.fengshui.common.services.AdvertisementPackageService;
import com.fengshui.common.shared.Request.AdvertisementPackage.CreateAdvertisementPackageRequestModel;
//import com.fengshui.common.shared.Response.Advertisement.CreateAdvertisementResponseModel;
import com.fengshui.common.shared.Request.AdvertisementPackage.GetAdvertisementPackageByIdRequestModel;
import com.fengshui.common.shared.Request.AdvertisementPackage.GetListAdvertisementPackageRequestModel;
import com.fengshui.common.shared.Request.AdvertisementPackage.UpdateAdvertisementPackageRequestModel;
import com.fengshui.common.shared.Response.Advertisement.GetAdvertisementByIdResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.CreateAdvertisementPackageResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.GetAdvertisementPackageByIdResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.GetListAdvertisementPackageResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.UpdateAdvertisementPackageResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementPackageServiceImpl implements AdvertisementPackageService {

    @Autowired
    IAdvertisementPackageRepository advertisementPackageRepository;

    @Override
    @Transactional
    public ResponseEntity<CreateAdvertisementPackageResponseModel> createAdvertisementPackage(CreateAdvertisementPackageRequestModel requestModel) {
        CreateAdvertisementPackageResponseModel response;
        try {
            AdvertisementPackageEntity packageEntity = AdvertisementPackageEntity.builder()
                    .packageName(requestModel.getPackageName())
                    .description(requestModel.getDescription())
                    .price(requestModel.getPrice())
                    .durationInDays(requestModel.getDurationInDays())
                    .maxAds(requestModel.getMaxAds())
                    .isActive(requestModel.getIsActive())
                    .build();

            AdvertisementPackageEntity savedPackage = advertisementPackageRepository.save(packageEntity);

//            AdvertisementPackageDTO dto = AdvertisementPackageMapper.toDTO(savedPackage);
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(new CreateAdvertisementPackageResponseModel(false, dto, null));
            response = new CreateAdvertisementPackageResponseModel(false, AdvertisementPackageMapper.toDTO(savedPackage), null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception error) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new CreateAdvertisementPackageResponseModel(true, null, error.getMessage()));
            response = new CreateAdvertisementPackageResponseModel(true, null, error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<GetAdvertisementPackageByIdResponseModel> getAdvertisementPackageById(GetAdvertisementPackageByIdRequestModel requestModel) {
        GetAdvertisementByIdResponseModel response;
        Optional<AdvertisementPackageEntity> optionalPackage = advertisementPackageRepository.findById(requestModel.getPackageId());

        if (optionalPackage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GetAdvertisementPackageByIdResponseModel(true, null, "Package not found"));
        }

        AdvertisementPackageEntity advertisementPackage = optionalPackage.get();
        AdvertisementPackageDTO dto = AdvertisementPackageMapper.toDTO(advertisementPackage);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetAdvertisementPackageByIdResponseModel(false, dto, null));
    }

    @Override
    public ResponseEntity<GetListAdvertisementPackageResponseModel> getListAdvertisementPackage(GetListAdvertisementPackageRequestModel requestModel) {
        GetListAdvertisementPackageResponseModel response;
        List<AdvertisementPackageDTO> packageList = advertisementPackageRepository.findAll()
                .stream()
                .map(AdvertisementPackageMapper::toDTO)
                .collect(Collectors.toList());

//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new GetListAdvertisementPackageResponseModel(false, packageList, null));
        response = new GetListAdvertisementPackageResponseModel(false, packageList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<UpdateAdvertisementPackageResponseModel> updateAdvertisementPackage(UpdateAdvertisementPackageRequestModel requestModel) {
        Optional<AdvertisementPackageEntity> optionalPackage = advertisementPackageRepository.findById(requestModel.getPackageId());

        if (optionalPackage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UpdateAdvertisementPackageResponseModel(true, null, "Package not found"));
        }

        try {
            AdvertisementPackageEntity packageEntity = optionalPackage.get();
            packageEntity.setPackageName(requestModel.getPackageName());
            packageEntity.setDescription(requestModel.getDescription());
            packageEntity.setPrice(requestModel.getPrice());
            packageEntity.setDurationInDays(requestModel.getDurationInDays());
            packageEntity.setMaxAds(requestModel.getMaxAds());
//            packageEntity.setActive(requestModel.getIsActive());

            AdvertisementPackageEntity updatedPackage = advertisementPackageRepository.save(packageEntity);

            AdvertisementPackageDTO dto = AdvertisementPackageMapper.toDTO(updatedPackage);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateAdvertisementPackageResponseModel(false, dto, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UpdateAdvertisementPackageResponseModel(true, null, e.getMessage()));
        }
    }

//    @Override
//    public ResponseEntity<DeleteAdvertisementPackageResponseModel> deleteAdvertisementPackage(DeleteAdvertisementPackageRequestModel requestModel) {
//        Optional<AdvertisementPackageEntity> optionalPackage = advertisementPackageRepository.findById(requestModel.getPackageId());
//
//        if (optionalPackage.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new DeleteAdvertisementPackageResponseModel(true, "Package not found"));
//        }
//
//        try {
//            AdvertisementPackageEntity packageEntity = optionalPackage.get();
//            advertisementPackageRepository.delete(packageEntity);
//
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new DeleteAdvertisementPackageResponseModel(false, "Package deleted successfully"));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new DeleteAdvertisementPackageResponseModel(true, e.getMessage()));
//        }
//    }
}
