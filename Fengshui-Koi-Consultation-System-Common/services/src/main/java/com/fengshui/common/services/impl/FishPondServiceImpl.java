package com.fengshui.common.services.impl;

import com.fengshui.common.aws.S3Client.S3Client;
import com.fengshui.common.repository.postgresql.IFengshuiElementRepository;
import com.fengshui.common.repository.postgresql.IFishPondRepository;
import com.fengshui.common.repository.postgresql.dto.FishPondDTO;
import com.fengshui.common.repository.postgresql.entities.FengshuiElementEntity;
import com.fengshui.common.repository.postgresql.entities.FishPondEntity;
import com.fengshui.common.repository.postgresql.mapper.FishPondMapper;
import com.fengshui.common.repository.postgresql.mapper.KoiFishMapper;
import com.fengshui.common.services.FishPondService;
import com.fengshui.common.shared.Constants.ImageType;
import com.fengshui.common.shared.Request.FishPond.CreateFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.GetFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.UpdateFishPondRequestModel;
import com.fengshui.common.shared.Response.FishPond.CreateFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.GetFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.UpdateFishPondResponseModel;
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
    IFengshuiElementRepository fengshuiElementRepository;

    @Override
    @Transactional
    public ResponseEntity<CreateFishPondResponseModel> createFishPond(CreateFishPondRequestModel requestModel) {
        CreateFishPondResponseModel response;
        try {
            // Upload fish pond pictures to S3
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

            // Fetch the Feng Shui element based on the provided fengShuiElementId
            FengshuiElementEntity fengshuiElement = fengshuiElementRepository
                    .findById(requestModel.getFengshuiElement())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Feng Shui Element ID"));

            // Build the Fish Pond entity
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
//                    .pondElement(requestModel.getPondElement())  // Feng Shui element
                    .pondLocation(requestModel.getPondLocation())
                    .pondOrientation(requestModel.getPondOrientation())
                    .pondPictures(uploadedImageUrls)
                    .fengshuiElement(fengshuiElement)
                    .deleted(false)
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
            // Find existing fish pond
            FishPondEntity existingFishPond = fishPondRepository.findById(requestModel.getFishPondId())
                    .orElseThrow(() -> new IllegalArgumentException("Fish Pond not found"));

            // Update fields if they are provided in the request
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
                existingFishPond.setPondOrientation(requestModel.getPondOrientation());
            }

            // Upload new pictures if provided
            List<String> uploadedImageUrls = new ArrayList<>();
            MultipartFile[] fishPondPictures = requestModel.getFishPondPictures();
            if (fishPondPictures != null) {
                for (MultipartFile picture : fishPondPictures) {
                    String imageUrl = s3Client.uploadImage(String.valueOf(ImageType.FISH_POND), picture);
                    uploadedImageUrls.add(imageUrl);
                }
                existingFishPond.setPondPictures(uploadedImageUrls);
            }

            // Set Feng Shui element if provided
            if (requestModel.getFengshuiElement() != null) {
                FengshuiElementEntity fengshuiElement = fengshuiElementRepository
                        .findById(requestModel.getFengshuiElement())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Feng Shui Element ID"));
                existingFishPond.setFengshuiElement(fengshuiElement);
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
}
