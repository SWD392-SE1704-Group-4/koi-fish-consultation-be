package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.AdvertisementDTO;
import com.fengshui.common.repository.postgresql.dto.AppUserDTO;
import com.fengshui.common.repository.postgresql.entities.AdvertisementEntity;
import com.fengshui.common.repository.postgresql.enums.AdvertisementStatus;

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
                .advertisementType(AdvertisementTypeMapper.toDTO(entity.getAdvertisementType()))
                .location(entity.getLocation())
                .contactInfo(entity.getContactInfo())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .viewsCount(entity.getViewsCount())
                .status(String.valueOf(entity.getStatus()))
                .verified(entity.isVerified())
                .expirationDate(entity.getExpirationDate())
                .koiFish(KoiFishMapper.toDTO(entity.getKoiFish()))
                .fishPond(FishPondMapper.toDTO(entity.getFishPond()))
                .additionalImages(entity.getAdditionalImages())
                .tags(entity.getTags())
                .postedBy(AppUserMapper.toDTO(entity.getPostedBy()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    // Convert from AdvertisementDTO to AdvertisementEntity
//    public static AdvertisementEntity toEntity(AdvertisementDTO dto) {
//        if (dto == null) {
//            return null;
//        }
//        return AdvertisementEntity.builder()
//                .id(dto.getAdvertisementId())
//                .title(dto.getTitle())
//                .description(dto.getDescription())
//                .location(dto.getLocation())
//                .contactInfo(dto.getContactInfo())
//                .advertisementType(AdvertisementTypeMapper.toEntity(dto.getAdvertisementType()))
//                .viewsCount(dto.getViewsCount())
//                .status(AdvertisementStatus.valueOf(dto.getStatus()))
//                .adminVerified(dto.isAdminVerified())
//                .expirationDate(dto.getExpirationDate())
//                .postedBy(dto.getPostedBy())
//                .additionalImages(dto.getAdditionalImages())
//                .tags(dto.getTags())
//                .build();
//    }
}
