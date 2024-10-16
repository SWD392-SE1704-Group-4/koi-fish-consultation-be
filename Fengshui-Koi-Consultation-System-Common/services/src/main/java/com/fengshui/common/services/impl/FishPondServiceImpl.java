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
import com.fengshui.common.shared.Response.FishPond.CreateFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.GetFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.UpdateFishPondResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
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
                    .pondElement(requestModel.getPondElement())  // Feng Shui element
                    .pondLocation(requestModel.getPondLocation())
                    .pondOrientation(requestModel.getPondOrientation())
                    .pondPictures(uploadedImageUrls)
//                    .fengshuiElement(fengshuiElement)
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
        List<FishPondDTO> fishPondList = fishPondRepository.findAll().stream().map(FishPondMapper::toDTO).toList();
        response = new GetFishPondResponseModel(false, fishPondList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // New Method: Update Fish Pond
//    @Override
//    @Transactional
//    public ResponseEntity<UpdateFishPondResponseModel> updateFishPond(Long id, UpdateFishPondRequestModel requestModel) {
//        UpdateFishPondResponseModel response;
//        try {
//            // Find existing fish pond
//            FishPondEntity existingFishPond = fishPondRepository.findById(id)
//                    .orElseThrow(() -> new IllegalArgumentException("Fish Pond not found"));
//
//            // Upload new pictures if provided
//            List<String> uploadedImageUrls = new ArrayList<>();
//            MultipartFile[] fishPondPictures = requestModel.getFishPondPictures();
//            if (fishPondPictures != null) {
//                for (MultipartFile picture : fishPondPictures) {
//                    String imageUrl = s3Client.uploadImage(String.valueOf(ImageType.FISH_POND), picture);
//                    uploadedImageUrls.add(imageUrl);
//                }
//            }
//
//            // Update fields
//            existingFishPond.setPondName(requestModel.getPondName());
//            existingFishPond.setPondShape(requestModel.getPondShape());
//            existingFishPond.setPondSize(requestModel.getPondSize());
//            existingFishPond.setPondDepth(requestModel.getPondDepth());
//            existingFishPond.setPondMaterial(requestModel.getPondMaterial());
//            existingFishPond.setHasWaterfall(requestModel.getHasWaterfall());
//            existingFishPond.setHasPlants(requestModel.getHasPlants());
//            existingFishPond.setHasRocks(requestModel.getHasRocks());
//            existingFishPond.setIsSaltwater(requestModel.getIsSaltwater());
//            existingFishPond.setNumKoiFish(requestModel.getNumKoiFish());
//            existingFishPond.setWaterCapacity(requestModel.getWaterCapacity());
//            existingFishPond.setPondElement(requestModel.getPondElement());
//            existingFishPond.setPondLocation(requestModel.getPondLocation());
//            existingFishPond.setPondOrientation(requestModel.getPondOrientation());
//            if (!uploadedImageUrls.isEmpty()) {
//                existingFishPond.setPondPictures(uploadedImageUrls);
//            }
//
//            // Save updated entity
//            fishPondRepository.save(existingFishPond);
//
//            // Create response
//            response = new UpdateFishPondResponseModel(false, FishPondMapper.toDTO(existingFishPond), null);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//
//        } catch (Exception error) {
//            response = new UpdateFishPondResponseModel(true, null, error.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
}
