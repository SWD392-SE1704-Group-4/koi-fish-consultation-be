package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.AdvertisementPackageDTO;
import com.fengshui.common.repository.postgresql.entities.AdvertisementPackageEntity;

public class AdvertisementPackageMapper {
    // Convert from AdvertisementPackageEntity to AdvertisementPackageDTO
    public static AdvertisementPackageDTO toDTO(AdvertisementPackageEntity entity) {
        if (entity == null) {
            return null;
        }
        return AdvertisementPackageDTO.builder()
                .packageId(entity.getId())
                .packageName(entity.getPackageName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .durationInDays(entity.getDurationInDays())
                .maxAds(entity.getMaxAds())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    // Convert from AdvertisementPackageDTO to AdvertisementPackageEntity
    public static AdvertisementPackageEntity toEntity(AdvertisementPackageDTO dto) {
        if (dto == null) {
            return null;
        }
        return AdvertisementPackageEntity.builder()
                .id(dto.getPackageId())
                .packageName(dto.getPackageName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .durationInDays(dto.getDurationInDays())
                .maxAds(dto.getMaxAds())
                .isActive(dto.getIsActive())
//                .createdAt(dto.getCreatedAt())
//                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
