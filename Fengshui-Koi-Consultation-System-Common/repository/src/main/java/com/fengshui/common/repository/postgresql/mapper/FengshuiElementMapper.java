package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.FengshuiElementDTO;
import com.fengshui.common.repository.postgresql.entities.FengshuiElementEntity;

public class FengshuiElementMapper {

    public static FengshuiElementDTO toDTO(FengshuiElementEntity entity) {
        if (entity == null) {
            return null;
        }
        return FengshuiElementDTO.builder()
                .elementId(entity.getId())
                .elementName(entity.getElementName())
                .elementColor(entity.getElementColor())
                .elementDirection(entity.getElementDirection())
                .elementSeason(entity.getElementSeason())
                .elementYinYang(entity.getElementYinYang())
                .elementAssociatedAnimals(entity.getElementAssociatedAnimals())
                .elementStrength(entity.getElementStrength())
                .createdAt(entity.getCreatedAt().toString())  // Format as string
                .updatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt().toString() : null)
                .build();
    }

    public static FengshuiElementEntity toEntity(FengshuiElementDTO dto) {
        if (dto == null) {
            return null;
        }
        return FengshuiElementEntity.builder()
                .Id(dto.getElementId())
                .elementName(dto.getElementName())
                .elementColor(dto.getElementColor())
                .elementDirection(dto.getElementDirection())
                .elementSeason(dto.getElementSeason())
                .elementYinYang(dto.getElementYinYang())
                .elementAssociatedAnimals(dto.getElementAssociatedAnimals())
                .elementStrength(dto.getElementStrength())
                .build();
    }
}

