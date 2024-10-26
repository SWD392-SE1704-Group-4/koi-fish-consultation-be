package com.fengshui.common.services.impl;

import com.fengshui.common.aws.S3Client.S3Client;
import com.fengshui.common.repository.postgresql.IAppUserRepository;
import com.fengshui.common.repository.postgresql.IFengshuiDirectionRepository;
import com.fengshui.common.repository.postgresql.IFengshuiElementRepository;
import com.fengshui.common.repository.postgresql.IFishPondRepository;
import com.fengshui.common.repository.postgresql.dto.FishPondDTO;
import com.fengshui.common.repository.postgresql.entities.*;
import com.fengshui.common.repository.postgresql.mapper.FishPondMapper;
import com.fengshui.common.repository.postgresql.mapper.KoiFishMapper;
import com.fengshui.common.services.FishPondService;
import com.fengshui.common.shared.Constants.ImageType;
import com.fengshui.common.shared.Request.FishPond.CreateFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.DeleteFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.GetFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.UpdateFishPondRequestModel;
import com.fengshui.common.shared.Response.FishPond.CreateFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.DeleteFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.GetFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.UpdateFishPondResponseModel;
import com.fengshui.common.shared.Response.KoiFish.DeleteKoiFishResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.fengshui.common")
public class FishPondServiceImpl implements FishPondService {

    @Autowired
    S3Client s3Client;

    @Autowired
    IFishPondRepository fishPondRepository;

    @Autowired
    IAppUserRepository appUserRepository;

    @Autowired
    IFengshuiDirectionRepository fengshuiDirectionRepository;

    @Override
    @Transactional
    public ResponseEntity<CreateFishPondResponseModel> createFishPond(CreateFishPondRequestModel requestModel) {
        CreateFishPondResponseModel response;
        try {
            MultipartFile[] fishPondPictures = requestModel.getFishPondPictures();
            List<String> uploadedImageUrls = new ArrayList<>();
            for (MultipartFile picture : fishPondPictures) {
                String imageUrl = s3Client.uploadImage(String.valueOf(ImageType.FISH_POND), picture);
                if (imageUrl != null) {
                    uploadedImageUrls.add(imageUrl);
                } else {
                    throw new IOException("Failed to upload one or more images.");
                }
            }
            FengshuiDirectionEntity pondDirection = fengshuiDirectionRepository
                    .findById(requestModel.getPondOrientation())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Feng Shui Direction ID"));

            AppUserEntity appUser = appUserRepository
                    .findById(requestModel.getCreatedBy())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid App user ID"));

            FishPondEntity fishPond = FishPondEntity.builder()
                    .pondName(requestModel.getPondName())
                    .pondShape(requestModel.getPondShape())
                    .pondSize(requestModel.getPondSize())
                    .pondDepth(requestModel.getPondDepth())
                    .pondMaterial(requestModel.getPondMaterial())
                    .hasWaterfall(requestModel.getHasWaterfall())
                    .hasPlants(requestModel.getHasPlants())
                    .hasRocks(requestModel.getHasRocks())
                    .isSaltwater(requestModel.getIsSaltwater())
                    .numKoiFish(requestModel.getNumKoiFish())
                    .waterCapacity(requestModel.getWaterCapacity())
                    .pondLocation(requestModel.getPondLocation())
                    .pondOrientation(pondDirection)
                    .pondPictures(uploadedImageUrls)
                    .createdBy(appUser)
                    .build();

            // Save the Fish Pond entity
            FishPondEntity newFishPond = fishPondRepository.save(fishPond);

            // Create response
            response = new CreateFishPondResponseModel(false, FishPondMapper.toDTO(newFishPond), null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception error) {
            response = new CreateFishPondResponseModel(true, null, error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<GetFishPondResponseModel> getListFishPond(GetFishPondRequestModel requestModel) {
        GetFishPondResponseModel response;
        List<FishPondDTO> fishPondList = fishPondRepository.findAll()
                .stream()
                .filter(fishPond -> !fishPond.isDeleted())
                .map(FishPondMapper::toDTO)
                .toList();
        response = new GetFishPondResponseModel(false, fishPondList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Override
    @Transactional
    public ResponseEntity<UpdateFishPondResponseModel> updateFishPond(UpdateFishPondRequestModel requestModel) {
        UpdateFishPondResponseModel response;
        try {
            FishPondEntity existingFishPond = fishPondRepository.findById(requestModel.getFishPondId())
                    .orElseThrow(() -> new IllegalArgumentException("Fish Pond not found"));

            if (requestModel.getPondName() != null) {
                existingFishPond.setPondName(requestModel.getPondName());
            }
            if (requestModel.getPondShape() != null) {
                existingFishPond.setPondShape(requestModel.getPondShape());
            }
            if (requestModel.getPondSize() > 0) {
                existingFishPond.setPondSize(requestModel.getPondSize());
            }
            if (requestModel.getPondDepth() > 0) {
                existingFishPond.setPondDepth(requestModel.getPondDepth());
            }
            if (requestModel.getPondMaterial() != null) {
                existingFishPond.setPondMaterial(requestModel.getPondMaterial());
            }
            if (requestModel.getHasWaterfall() != null) {
                existingFishPond.setHasWaterfall(requestModel.getHasWaterfall());
            }
            if (requestModel.getHasPlants() != null) {
                existingFishPond.setHasPlants(requestModel.getHasPlants());
            }
            if (requestModel.getHasRocks() != null) {
                existingFishPond.setHasRocks(requestModel.getHasRocks());
            }
            if (requestModel.getIsSaltwater() != null) {
                existingFishPond.setIsSaltwater(requestModel.getIsSaltwater());
            }
            if (requestModel.getNumKoiFish() > 0) {
                existingFishPond.setNumKoiFish(requestModel.getNumKoiFish());
            }
            if (requestModel.getWaterCapacity() > 0) {
                existingFishPond.setWaterCapacity(requestModel.getWaterCapacity());
            }
            if (requestModel.getPondLocation() != null) {
                existingFishPond.setPondLocation(requestModel.getPondLocation());
            }
            if (requestModel.getPondOrientation() != null) {
                FengshuiDirectionEntity pondDirection = fengshuiDirectionRepository
                        .findById(requestModel.getPondOrientation())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Feng Shui Direction ID"));
                existingFishPond.setPondOrientation(pondDirection);
            }

            List<String> uploadedImageUrls = new ArrayList<>();
            MultipartFile[] fishPondPictures = requestModel.getFishPondPictures();
            if (fishPondPictures != null) {
                for (MultipartFile picture : fishPondPictures) {
                    String imageUrl = s3Client.uploadImage(String.valueOf(ImageType.FISH_POND), picture);
                    uploadedImageUrls.add(imageUrl);
                }
                existingFishPond.setPondPictures(uploadedImageUrls);
            }

            // Save the updated fish pond entity
            FishPondEntity updatedFishPond = fishPondRepository.save(existingFishPond);

            // Create response
            response = new UpdateFishPondResponseModel(false, FishPondMapper.toDTO(updatedFishPond), null);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception error) {
            response = new UpdateFishPondResponseModel(true, null, error);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public ResponseEntity<DeleteFishPondResponseModel> deleteFishPond(DeleteFishPondRequestModel requestModel) {
        DeleteFishPondResponseModel response;
        try {
            FishPondEntity existingFishPond = fishPondRepository.findById(requestModel.getFishPondId())
                    .orElseThrow(() -> new IllegalArgumentException("Fish Pond not found"));

            existingFishPond.setDeleted(true);

            fishPondRepository.save(existingFishPond);

            response = new DeleteFishPondResponseModel(false, null, "Fish Pond deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception error) {
            response = new DeleteFishPondResponseModel(true, null, error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
