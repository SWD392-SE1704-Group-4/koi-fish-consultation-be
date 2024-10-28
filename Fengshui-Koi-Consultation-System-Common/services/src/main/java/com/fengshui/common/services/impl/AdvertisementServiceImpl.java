package com.fengshui.common.services.impl;

import com.fengshui.common.aws.Cognito.CognitoUserPool;
import com.fengshui.common.aws.S3Client.S3Client;
import com.fengshui.common.repository.postgresql.*;
import com.fengshui.common.repository.postgresql.dto.AdvertisementDTO;
import com.fengshui.common.repository.postgresql.dto.AdvertisementTypeDTO;
import com.fengshui.common.repository.postgresql.dto.FishPondDTO;
import com.fengshui.common.repository.postgresql.entities.*;
import com.fengshui.common.repository.postgresql.enums.AdvertisementStatus;
import com.fengshui.common.repository.postgresql.mapper.AdvertisementMapper;
import com.fengshui.common.repository.postgresql.mapper.AdvertisementTypeMapper;
import com.fengshui.common.repository.postgresql.mapper.FishPondMapper;
import com.fengshui.common.services.AdvertisementService;
import com.fengshui.common.shared.Constants.ImageType;
import com.fengshui.common.shared.Request.Advertisement.*;
import com.fengshui.common.shared.Response.Advertisement.*;
import com.fengshui.common.shared.Response.FishPond.GetFishPondByUserResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserResponse;

import java.io.IOException;
import java.util.*;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    S3Client s3Client;

    @Autowired
    CognitoUserPool cognitoUserPool;

    @Autowired
    IAdvertisementRepository advertisementRepository;

    @Autowired
    IAppUserRepository appUserRepository;

    @Autowired
    IAdvertisementTypeRepository advertisementTypeRepository;

    @Autowired
    IKoiFishRepository koiFishRepository;

    @Autowired
    IFishPondRepository fishPondRepository;

    @Override
    @Transactional
    public ResponseEntity<CreateAdvertisementResponseModel> createAdvertisement(CreateAdvertisementRequestModel requestModel) {
        CreateAdvertisementResponseModel response;
        try {
            MultipartFile[] additionalImages = requestModel.getAdditionalImages();
            List<String> uploadedImageUrls = new ArrayList<>();

            if (additionalImages != null && !Arrays.stream(additionalImages).toList().isEmpty()) {
                for (MultipartFile image : additionalImages) {
                    String imageUrl = s3Client.uploadImage(String.valueOf(ImageType.ADVERTISEMENT), image);
                    if (imageUrl != null) {
                        uploadedImageUrls.add(imageUrl);
                    } else {
                        throw new IOException("Failed to upload one or more images.");
                    }
                }
            }
            KoiFishEntity koiFish = null;
            FishPondEntity fishPond = null;
            if(requestModel.getKoiFishId() != null){
                koiFish = koiFishRepository
                        .findById(requestModel.getKoiFishId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Koi Fish ID"));
            }
            if(requestModel.getFishPondId() != null){
                fishPond = fishPondRepository
                        .findById(requestModel.getFishPondId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Fish pond ID"));
            }


            AdvertisementTypeEntity adsType = advertisementTypeRepository
                    .findById(requestModel.getAdvertisementType())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Ads ID"));

            AppUserEntity appUser = appUserRepository
                    .findById(requestModel.getPostedBy())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid App user ID"));

            AdvertisementEntity advertisement = AdvertisementEntity.builder()
                    .title(requestModel.getTitle())
                    .advertisementType(adsType)
                    .description(requestModel.getDescription())
                    .koiFish(koiFish)
                    .fishPond(fishPond)
                    .postedBy(appUser)
                    .additionalImages(uploadedImageUrls)
                    .location(requestModel.getLocation())
                    .contactInfo(requestModel.getContactInfo())
                    .address(requestModel.getAddress())
                    .phone(requestModel.getPhone())
                    .build();

            AdvertisementEntity newAdvertisement = advertisementRepository.save(advertisement);
            response = new CreateAdvertisementResponseModel(false, AdvertisementMapper.toDTO(newAdvertisement), null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception error) {
            response = new CreateAdvertisementResponseModel(true, null, error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<GetListAdvertisementResponseModel> getListAdvertisement(GetListAdvertisementRequestModel requestModel) {
        GetListAdvertisementResponseModel response;
        List<AdvertisementDTO> advertisementList = this.advertisementRepository.findAll()
                .stream()
                .filter(advertisement -> !advertisement.isDeleted())
                .map(AdvertisementMapper::toDTO)
                .toList();
        response = new GetListAdvertisementResponseModel(false, advertisementList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<GetListAdvertisementByCreatorResponseModel> getListAdvertisementByCreator(GetListAdvertisementByCreatorRequestModel requestModel) {
        UUID userId = requestModel.getAppUserId();

        List<AdvertisementDTO> adsList = advertisementRepository.findAdsByCreator(userId)
                .stream()
                .filter(ads -> !ads.isDeleted())
                .map(AdvertisementMapper::toDTO)
                .toList();

        GetListAdvertisementByCreatorResponseModel response = new GetListAdvertisementByCreatorResponseModel(false, adsList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<GetAdvertisementByIdResponseModel> getAdvertisementById(GetAdvertisementByIdRequestModel requestModel) {
        GetAdvertisementByIdResponseModel response;
        Optional<AdvertisementEntity> optionalAdvertisement = this.advertisementRepository.findById(requestModel.getAdvertisementId());

        if (optionalAdvertisement.isEmpty() || optionalAdvertisement.get().isDeleted()) {
            // Return a response indicating that the advertisement was not found or is deleted
            response = new GetAdvertisementByIdResponseModel(true, null, "Advertisement not found or has been deleted.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        AdvertisementEntity advertisement = optionalAdvertisement.get();
        AdvertisementDTO dto = AdvertisementMapper.toDTO(advertisement);

        response = new GetAdvertisementByIdResponseModel(false, dto, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<GetListAdvertisementResponseModel> getListAdvertisementType(GetListAdvertisementRequestModel requestModel) {
        List<AdvertisementTypeDTO> advertisementTypeList = this.advertisementTypeRepository.findAll()
                .stream()
                .map(AdvertisementTypeMapper::toDTO)
                .toList();

        GetListAdvertisementResponseModel response = new GetListAdvertisementResponseModel(false, advertisementTypeList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Override
    public ResponseEntity<UpdateAdvertisementResponseModel> updateAdvertisement(UpdateAdvertisementRequestModel requestModel) {
        return null;
    }

    @Override
    public ResponseEntity<DeleteAdvertisementResponseModel> deleteAdvertisement(DeleteAdvertisementRequestModel requestModel) {
        return null;
    }

    @Override
    public ResponseEntity<VerifyAdvertisementResponseModel> verifyAdvertisement(VerifyAdvertisementRequestModel requestModel) {
        VerifyAdvertisementResponseModel responseModel;

        Optional<AdvertisementEntity> advertisementOptional = advertisementRepository.findById(requestModel.getAdvertisementId());

        if (advertisementOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new VerifyAdvertisementResponseModel(true, "Advertisement not found", null));
        }

        AdvertisementEntity advertisement = advertisementOptional.get();
        advertisement.setVerified(true);
        advertisement.setStatus(AdvertisementStatus.APPROVED); // Assuming APPROVED is the verified status

        advertisementRepository.save(advertisement);

        // Return success response
        responseModel = new VerifyAdvertisementResponseModel(false, "Advertisement verified successfully", null);
        return ResponseEntity.status(HttpStatus.OK).body(responseModel);
    }

    @Override
    public ResponseEntity<DenyAdvertisementResponseModel> denyAdvertisement(DenyAdvertisementRequestModel requestModel) {
        DenyAdvertisementResponseModel responseModel;

        Optional<AdvertisementEntity> advertisementOptional = advertisementRepository.findById(requestModel.getAdvertisementId());

        if (advertisementOptional.isEmpty()) {
            // If the advertisement does not exist, return a 404 response
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DenyAdvertisementResponseModel(true, "Advertisement not found", null));
        }

        // Get the advertisement and update its status
        AdvertisementEntity advertisement = advertisementOptional.get();
        advertisement.setVerified(false);
        advertisement.setStatus(AdvertisementStatus.REJECTED); // Assuming REJECTED is the denied status

        // Save the changes to the repository
        advertisementRepository.save(advertisement);

        responseModel = new DenyAdvertisementResponseModel(false, "Advertisement denied successfully", null);
        return ResponseEntity.status(HttpStatus.OK).body(responseModel);
    }
}
