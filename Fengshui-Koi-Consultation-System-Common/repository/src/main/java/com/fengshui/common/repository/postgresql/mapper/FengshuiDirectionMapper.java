package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.FengshuiDirectionDTO;
import com.fengshui.common.repository.postgresql.entities.FengshuiDirectionEntity;

public class FengshuiDirectionMapper {
    public static FengshuiDirectionDTO toDTO(FengshuiDirectionEntity entity) {
        if (entity == null) {
            return null;
        }

        return FengshuiDirectionDTO.builder()
                .directionId(entity.getDirectionId())
                .directionName(entity.getDirectionName())
                .description(entity.getDescription())
                .elementAssociation(entity.getElementAssociation())
                .yinYangBalance(entity.getYinYangBalance())
                .favorableAttributes(entity.getFavorableAttributes())
                .unfavorableAttributes(entity.getUnfavorableAttributes())
                .secondaryDirection(entity.getSecondaryDirection())
                .fengshuiTips(entity.getFengshuiTips())
                .associatedColor(entity.getAssociatedColor())
                .associatedShape(entity.getAssociatedShape())
                .fengshuiElement(FengshuiElementMapper.toDTO(entity.getFengshuiElement()))
                .build();
    }

    public static FengshuiDirectionEntity toEntity(FengshuiDirectionDTO dto) {
        if (dto == null) {
            return null;
        }

        return FengshuiDirectionEntity.builder()
                .directionId(dto.getDirectionId())
                .directionName(dto.getDirectionName())
                .description(dto.getDescription())
                .elementAssociation(dto.getElementAssociation())
                .yinYangBalance(dto.getYinYangBalance())
                .favorableAttributes(dto.getFavorableAttributes())
                .unfavorableAttributes(dto.getUnfavorableAttributes())
                .secondaryDirection(dto.getSecondaryDirection())
                .fengshuiTips(dto.getFengshuiTips())
                .associatedColor(dto.getAssociatedColor())
                .associatedShape(dto.getAssociatedShape())
                .fengshuiElement(FengshuiElementMapper.toEntity(dto.getFengshuiElement()))
                .build();
    }
}
