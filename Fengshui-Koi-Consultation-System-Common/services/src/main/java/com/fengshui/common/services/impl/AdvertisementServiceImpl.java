package com.fengshui.common.services.impl;

import com.fengshui.common.aws.Cognito.CognitoUserPool;
import com.fengshui.common.aws.S3Client.S3Client;
import com.fengshui.common.repository.postgresql.IAdvertisementRepository;
import com.fengshui.common.repository.postgresql.IFengshuiElementRepository;
import com.fengshui.common.repository.postgresql.IKoiFishRepository;
import com.fengshui.common.repository.postgresql.dto.AdvertisementDTO;
import com.fengshui.common.repository.postgresql.entities.AdvertisementEntity;
import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import com.fengshui.common.repository.postgresql.mapper.AdvertisementMapper;
import com.fengshui.common.services.AdvertisementService;
import com.fengshui.common.shared.Constants.ImageType;
import com.fengshui.common.shared.Request.Advertisement.CreateAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.DeleteAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.GetListAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.UpdateAdvertisementRequestModel;
import com.fengshui.common.shared.Response.Advertisement.CreateAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.DeleteAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.UpdateAdvertisementResponseModel;
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
    IKoiFishRepository koiFishRepository;

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
            KoiFishEntity koiFish = koiFishRepository
                    .findById(requestModel.getKoiFishId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Koi Fish ID"));
            AdvertisementEntity advertisement = AdvertisementEntity.builder()
                    .title(requestModel.getTitle())
                    .description(requestModel.getDescription())
                    .location(requestModel.getLocation())
                    .contactInfo(requestModel.getContactInfo())
                    .quantity(requestModel.getQuantity())
                    .koiFish(koiFish)
                    .postedBy(requestModel.getPostedBy())
                    .additionalImages(uploadedImageUrls)
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
                .map(advertisement -> {
                    AdvertisementDTO dto = AdvertisementMapper.toDTO(advertisement);
                    if (dto.getPostedBy() != null) {
                        try {
                            AdminGetUserResponse userResponse = cognitoUserPool.getUserById(dto.getPostedBy().toString());
                            if (userResponse != null) {
                                Map<String, String> userInfo = new HashMap<>();
                                userResponse.userAttributes().forEach(attribute ->
                                        userInfo.put(attribute.name(), attribute.value())
                                );
                                dto.setUserInfo(userInfo);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return dto;
                })
                .toList();
        response = new GetListAdvertisementResponseModel(false, advertisementList, null);
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
}
