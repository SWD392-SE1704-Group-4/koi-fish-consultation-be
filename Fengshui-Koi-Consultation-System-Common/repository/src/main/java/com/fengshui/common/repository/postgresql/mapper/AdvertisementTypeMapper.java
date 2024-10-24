package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.AdvertisementTypeDTO;
import com.fengshui.common.repository.postgresql.entities.AdvertisementTypeEntity;

public class AdvertisementTypeMapper {
    public static AdvertisementTypeDTO toDTO(AdvertisementTypeEntity entity) {
        if (entity == null) {
            return null;
        }
        return AdvertisementTypeDTO.builder()
                .id(entity.getId())
                .typeName(entity.getTypeName())
                .description(entity.getDescription())
                .build();
    }

    public static AdvertisementTypeEntity toEntity(AdvertisementTypeDTO dto) {
        if (dto == null) {
            return null;
        }
        return AdvertisementTypeEntity.builder()
                .id(dto.getId())
                .typeName(dto.getTypeName())
                .description(dto.getDescription())
                .build();
    }
}
