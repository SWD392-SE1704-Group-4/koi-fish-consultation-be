package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.FishPondDTO;
import com.fengshui.common.repository.postgresql.entities.FishPondEntity;

public class FishPondMapper {
    // Convert from FishPondEntity to FishPondDTO
    public static FishPondDTO toDTO(FishPondEntity entity) {
        if (entity == null) {
            return null;
        }
        return FishPondDTO.builder()
                .pondId(entity.getPondId())
                .pondName(entity.getPondName())
                .pondShape(entity.getPondShape())
                .pondSize(entity.getPondSize())
                .pondDepth(entity.getPondDepth())
                .pondMaterial(entity.getPondMaterial())
                .hasWaterfall(entity.getHasWaterfall())
                .hasPlants(entity.getHasPlants())
                .hasRocks(entity.getHasRocks())
                .isSaltwater(entity.getIsSaltwater())
                .numKoiFish(entity.getNumKoiFish())
                .waterCapacity(entity.getWaterCapacity())
                .pondLocation(entity.getPondLocation())
                .pondOrientation(FengshuiDirectionMapper.toDTO(entity.getPondOrientation())) // Convert to DTO
                .build();
    }

    // Convert from FishPondDTO to FishPondEntity
    public static FishPondEntity toEntity(FishPondDTO dto) {
        if (dto == null) {
            return null;
        }
        return FishPondEntity.builder()
                .pondId(dto.getPondId())
                .pondName(dto.getPondName())
                .pondShape(dto.getPondShape())
                .pondSize(dto.getPondSize())
                .pondDepth(dto.getPondDepth())
                .pondMaterial(dto.getPondMaterial())
                .hasWaterfall(dto.getHasWaterfall())
                .hasPlants(dto.getHasPlants())
                .hasRocks(dto.getHasRocks())
                .isSaltwater(dto.getIsSaltwater())
                .numKoiFish(dto.getNumKoiFish())
                .waterCapacity(dto.getWaterCapacity())
                .pondLocation(dto.getPondLocation())
                .pondOrientation(FengshuiDirectionMapper.toEntity(dto.getPondOrientation())) // Convert to Entity
                .build();
    }
}
