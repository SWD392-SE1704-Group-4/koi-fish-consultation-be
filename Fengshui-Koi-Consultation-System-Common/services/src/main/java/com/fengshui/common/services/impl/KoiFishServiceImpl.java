package com.fengshui.common.services.impl;

import com.fengshui.common.aws.S3Client.S3Client;
import com.fengshui.common.repository.postgresql.IAdvertisementRepository;
import com.fengshui.common.repository.postgresql.IFengshuiElementRepository;
import com.fengshui.common.repository.postgresql.IKoiFishRepository;
import com.fengshui.common.repository.postgresql.dto.KoiFishDTO;
import com.fengshui.common.repository.postgresql.entities.FengshuiElementEntity;
import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import com.fengshui.common.repository.postgresql.enums.AdvertisementStatus;
import com.fengshui.common.repository.postgresql.mapper.KoiFishMapper;
import com.fengshui.common.services.KoiFishService;
import com.fengshui.common.shared.Constants.ImageType;
import com.fengshui.common.shared.Request.KoiFish.*;
import com.fengshui.common.shared.Response.BaseResponseModel;
import com.fengshui.common.shared.Response.KoiFish.*;
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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@ComponentScan(basePackages = "com.fengshui.common")
public class KoiFishServiceImpl implements KoiFishService {

    @Autowired
    S3Client s3Client;

    @Autowired
    IKoiFishRepository koiFishRepository;

    @Autowired
    IFengshuiElementRepository fengshuiElementRepository;

    @Autowired
    IAdvertisementRepository advertisementRepository;

    @Override
    @Transactional
    public ResponseEntity<CreateKoiFishResponseModel> createKoiFish(CreateKoiFishRequestModel requestModel) {
        CreateKoiFishResponseModel response;
        try {
            // Upload koi fish pictures to S3
            MultipartFile[] koiFishPictures = requestModel.getKoiFishPictures();
            List<String> uploadedImageUrls = new ArrayList<>();
            for (MultipartFile picture : koiFishPictures) {
                String imageUrl = s3Client.uploadImage(String.valueOf(ImageType.KOI_FISH), picture);
                if (imageUrl != null) {
                    uploadedImageUrls.add(imageUrl);
                } else {
                    throw new IOException("Failed to upload one or more images.");
                }
            }

            FengshuiElementEntity fengshuiElement = fengshuiElementRepository
                    .findById(requestModel.getFengshuiElement())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Feng Shui Element ID"));

            KoiFishEntity koiFish = KoiFishEntity.builder()
                    .koiFishName(requestModel.getKoiFishName())
                    .koiFishColor(requestModel.getKoiFishColor())
                    .koiFishSize(requestModel.getKoiFishSize())
                    .koiFishAge(requestModel.getKoiFishAge())
                    .koiFishPictures(uploadedImageUrls)
                    .fengshuiElement(fengshuiElement)
                    .symbolicMeaning(requestModel.getSymbolicMeaning())
                    .energyType(requestModel.getEnergyType())
                    .favorableNumber(requestModel.getFavorableNumber())
                    .favorableColor(requestModel.getFavorableColor())
                    .koiFishOrigin(requestModel.getKoiFishOrigin())
                    .koiFishDescription(requestModel.getKoiFishDescription())
                    .koiFishPrice(requestModel.getKoiFishPrice())
                    .deleted(false)
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
        List<KoiFishDTO> koiList = this.koiFishRepository.findAll()
                .stream()
                .filter(koiFish -> !koiFish.isDeleted())
                .map(KoiFishMapper::toDTO)
                .toList();
        response = new GetKoiFishResponseModel(false, koiList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    @Transactional
    public ResponseEntity<UpdateKoiFishResponseModel> updateKoiFish(UpdateKoiFishRequestModel requestModel) {
        UpdateKoiFishResponseModel response;
        try {
            KoiFishEntity existingKoiFish = koiFishRepository.findById(requestModel.getKoiFishId())
                    .orElseThrow(() -> new IllegalArgumentException("Koi Fish not found"));

            if (requestModel.getKoiFishName() != null) {
                existingKoiFish.setKoiFishName(requestModel.getKoiFishName());
            }
            if (requestModel.getKoiFishColor() != null) {
                existingKoiFish.setKoiFishColor(requestModel.getKoiFishColor());
            }
            if (requestModel.getKoiFishSize() > 0) {
                existingKoiFish.setKoiFishSize(requestModel.getKoiFishSize());
            }
            if (requestModel.getKoiFishAge() > 0) {
                existingKoiFish.setKoiFishAge(requestModel.getKoiFishAge());
            }
            if (requestModel.getSymbolicMeaning() != null) {
                existingKoiFish.setSymbolicMeaning(requestModel.getSymbolicMeaning());
            }
            if (requestModel.getEnergyType() != null) {
                existingKoiFish.setEnergyType(requestModel.getEnergyType());
            }
            if (requestModel.getFavorableNumber() > 0) {
                existingKoiFish.setFavorableNumber(requestModel.getFavorableNumber());
            }
            if (requestModel.getFavorableColor() != null) {
                existingKoiFish.setFavorableColor(requestModel.getFavorableColor());
            }
            if (requestModel.getKoiFishOrigin() != null) {
                existingKoiFish.setKoiFishOrigin(requestModel.getKoiFishOrigin());
            }
            if (requestModel.getKoiFishDescription() != null) {
                existingKoiFish.setKoiFishDescription(requestModel.getKoiFishDescription());
            }
            if (requestModel.getKoiFishPrice() > 0) {
                existingKoiFish.setKoiFishPrice(requestModel.getKoiFishPrice());
            }


            existingKoiFish.setKoiFishPictures(requestModel.getKoiFishPictures());

            if (requestModel.getFengshuiElement() != null) {
                FengshuiElementEntity fengshuiElement = fengshuiElementRepository
                        .findById(requestModel.getFengshuiElement())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Feng Shui Element ID"));
                existingKoiFish.setFengshuiElement(fengshuiElement);
            }

            if (requestModel.getNewKoiFishPictures() != null) {
                List<String> currentPictures = existingKoiFish.getKoiFishPictures() != null
                        ? new ArrayList<>(existingKoiFish.getKoiFishPictures())
                        : new ArrayList<>();

                for (MultipartFile newPicture : requestModel.getNewKoiFishPictures()) {
                    String imageUrl = s3Client.uploadImage(String.valueOf(ImageType.KOI_FISH), newPicture);
                    if (imageUrl != null) {
                        currentPictures.add(imageUrl);
                    } else {
                        throw new IOException("Failed to upload one or more images.");
                    }
                }
                existingKoiFish.setKoiFishPictures(currentPictures);
            }

            KoiFishEntity updatedKoiFish = koiFishRepository.save(existingKoiFish);

            response = new UpdateKoiFishResponseModel(false, KoiFishMapper.toDTO(updatedKoiFish), null);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception error) {
            response = new UpdateKoiFishResponseModel(true, null, error);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<DeleteKoiFishResponseModel> deleteKoiFish(DeleteKoiFishRequestModel requestModel) {
        DeleteKoiFishResponseModel response;
        try {
            // Fetch the KoiFishEntity by ID
            KoiFishEntity existingKoiFish = koiFishRepository.findById(requestModel.getKoiFishId())
                    .orElseThrow(() -> new IllegalArgumentException("Koi Fish not found"));

            // Check if the KoiFishEntity is referenced in any active advertisement
            boolean isReferencedInActiveAd = advertisementRepository.existsByKoiFishAndStatus(existingKoiFish, AdvertisementStatus.APPROVED);
            if (isReferencedInActiveAd) {
                response = new DeleteKoiFishResponseModel(true, null, "Koi Fish cannot be deleted as it is associated with an active advertisement.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            // Soft delete the KoiFishEntity by setting its 'deleted' flag to true
            existingKoiFish.setDeleted(true);
            koiFishRepository.save(existingKoiFish);

            response = new DeleteKoiFishResponseModel(false, null, "Koi Fish soft deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception error) {
            response = new DeleteKoiFishResponseModel(true, null, "Error: " + error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<GetKoiFishByElementNameResponseModel> getListKoiFishByElementName(GetKoiFishByElementNameRequestModel requestModel) {
        GetKoiFishByElementNameResponseModel response;
        try {
            // Retrieve koi fish by Feng Shui element name and not deleted
            List<KoiFishEntity> koiFishList = koiFishRepository.findByFengshuiElement_ElementNameAndDeletedFalse(requestModel.getElementName());

            // Map entities to DTOs for the response
            List<KoiFishDTO> koiFishDTOs = koiFishList.stream()
                    .map(KoiFishMapper::toDTO)
                    .collect(Collectors.toList());

            // Build response with mapped DTOs
            response = new GetKoiFishByElementNameResponseModel(false, koiFishDTOs, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            // Return error response in case of failure
            response = new GetKoiFishByElementNameResponseModel(true, null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
