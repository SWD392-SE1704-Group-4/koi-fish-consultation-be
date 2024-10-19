package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.AdvertisementDTO;
import com.fengshui.common.repository.postgresql.entities.AdvertisementEntity;

public class AdvertisementMapper {
    // Convert from AdvertisementEntity to AdvertisementDTO
    public static AdvertisementDTO toDTO(AdvertisementEntity entity) {
        if (entity == null) {
            return null;
        }
        return AdvertisementDTO.builder()
                .advertisementId(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .location(entity.getLocation())
                .contactInfo(entity.getContactInfo())
                .advertisementType(entity.getAdvertisementType())
                .viewsCount(entity.getViewsCount())
                .quantity(entity.getQuantity())
                .status(entity.getStatus())
                .adminVerified(entity.isAdminVerified())
                .expirationDate(entity.getExpirationDate())
                .koiFish(KoiFishMapper.toDTO(entity.getKoiFish()))
                .koiFishName(entity.getKoiFish() != null ? entity.getKoiFish().getKoiFishName() : null)
                .postedBy(entity.getPostedBy())
                .additionalImages(entity.getAdditionalImages())
                .tags(entity.getTags())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    // Convert from AdvertisementDTO to AdvertisementEntity
    public static AdvertisementEntity toEntity(AdvertisementDTO dto) {
        if (dto == null) {
            return null;
        }
        return AdvertisementEntity.builder()
                .id(dto.getAdvertisementId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .contactInfo(dto.getContactInfo())
                .advertisementType(dto.getAdvertisementType())
                .quantity(dto.getQuantity())
                .viewsCount(dto.getViewsCount())
                .status(dto.getStatus())
                .adminVerified(dto.isAdminVerified())
                .expirationDate(dto.getExpirationDate())
                .postedBy(dto.getPostedBy())
                .additionalImages(dto.getAdditionalImages())
                .tags(dto.getTags())
                .build();
    }
}
