package com.fengshui.common.services.impl;

import com.fengshui.common.aws.S3Client.S3Client;
import com.fengshui.common.repository.postgresql.IFengshuiElementRepository;
import com.fengshui.common.repository.postgresql.IKoiFishRepository;
import com.fengshui.common.repository.postgresql.dto.KoiFishDTO;
import com.fengshui.common.repository.postgresql.entities.FengshuiElementEntity;
import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import com.fengshui.common.repository.postgresql.mapper.KoiFishMapper;
import com.fengshui.common.services.KoiFishService;
import com.fengshui.common.shared.Constants.ImageType;
import com.fengshui.common.shared.Request.KoiFish.CreateKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.GetKoiFishRequestModel;
import com.fengshui.common.shared.Response.BaseResponseModel;
import com.fengshui.common.shared.Response.KoiFish.CreateKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.GetKoiFishResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.fengshui.common")
public class KoiFishServiceImpl implements KoiFishService {

    @Autowired
    S3Client s3Client;

    @Autowired
    IKoiFishRepository koiFishRepository;

    @Autowired
    IFengshuiElementRepository fengshuiElementRepository;

    @Override
    @Transactional
    public ResponseEntity<CreateKoiFishResponseModel> createKoiFish(CreateKoiFishRequestModel requestModel) {
        CreateKoiFishResponseModel response;
        try {
            // Upload koi fish pictures to S3
            MultipartFile[] koiFishPictures = requestModel.getKoiFishPicture();
            List<String> uploadedImageUrls = new ArrayList<>();
            for (MultipartFile picture : koiFishPictures) {
                String imageUrl = s3Client.uploadImage(String.valueOf(ImageType.KOI_FISH), picture);
                if (imageUrl != null) {
                    uploadedImageUrls.add(imageUrl);
                } else {
                    throw new IOException("Failed to upload one or more images.");
                }
            }

            // Fetch the Feng Shui element based on the provided fengShuiElementId
            FengshuiElementEntity fengshuiElement = fengshuiElementRepository
                    .findById(requestModel.getFengShuiElementId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Feng Shui Element ID"));

            // Build the koi fish entity
            KoiFishEntity koiFish = KoiFishEntity.builder()
                    .koiFishName(requestModel.getKoiFishName())
                    .koiFishColor(requestModel.getKoiFishColor())
                    .koiFishSize(requestModel.getKoiFishSize())
                    .koiFishAge(requestModel.getKoiFishAge())
                    .koiFishPictures(uploadedImageUrls)
                    .fengshuiElement(fengshuiElement) // Set the Feng Shui element
                    .symbolicMeaning(requestModel.getSymbolicMeaning())
                    .energyType(requestModel.getEnergyType())
                    .favorableNumber(requestModel.getFavorableNumber())
                    .favorableColor(requestModel.getFavorableColor())
                    .koiFishOrigin(requestModel.getKoiFishOrigin())
                    .koiFishDescription(requestModel.getKoiFishDescription())
                    .koiFishPrice(requestModel.getKoiFishPrice())
                    .build();

            // Save the koi fish entity
            KoiFishEntity newKoiFish = koiFishRepository.save(koiFish);

            // Create response
            response = new CreateKoiFishResponseModel(false, KoiFishMapper.toDTO(newKoiFish), null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception error) {
            response = new CreateKoiFishResponseModel(true, null, error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @Override
    public ResponseEntity<GetKoiFishResponseModel> getListKoiFish(GetKoiFishRequestModel requestModel) {
        GetKoiFishResponseModel response;
        List<KoiFishDTO> koiList = this.koiFishRepository.findAll().stream().map(KoiFishMapper::toDTO).toList();
        response = new GetKoiFishResponseModel(false, koiList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
