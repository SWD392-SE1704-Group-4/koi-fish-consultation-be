package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.KoiFishDTO;
import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;

public class KoiFishMapper {

    // Convert from KoiFishEntity to KoiFishDTO
    public static KoiFishDTO toDTO(KoiFishEntity entity) {
        if (entity == null) {
            return null;
        }
        return KoiFishDTO.builder()
                .id(entity.getId())
                .koiFishName(entity.getKoiFishName())
                .koiFishColor(entity.getKoiFishColor())
                .koiFishSize(entity.getKoiFishSize())
                .koiFishAge(entity.getKoiFishAge())
                .koiFishPictures(entity.getKoiFishPictures())
                .fengshuiElement(FengshuiElementMapper.toDTO(entity.getFengshuiElement()))
                .symbolicMeaning(entity.getSymbolicMeaning())
                .energyType(entity.getEnergyType())
                .favorableNumber(entity.getFavorableNumber())
                .favorableColor(entity.getFavorableColor())
                .koiFishOrigin(entity.getKoiFishOrigin())
                .koiFishDescription(entity.getKoiFishDescription())
                .koiFishPrice(entity.getKoiFishPrice())
                .build();
    }

    // Convert from KoiFishDTO to KoiFishEntity
    public static KoiFishEntity toEntity(KoiFishDTO dto) {
        if (dto == null) {
            return null;
        }
        return KoiFishEntity.builder()
                .Id(dto.getId())
                .koiFishName(dto.getKoiFishName())
                .koiFishColor(dto.getKoiFishColor())
                .koiFishSize(dto.getKoiFishSize())
                .koiFishAge(dto.getKoiFishAge())
                .koiFishPictures(dto.getKoiFishPictures())
                .symbolicMeaning(dto.getSymbolicMeaning())
                .energyType(dto.getEnergyType())
                .favorableNumber(dto.getFavorableNumber())
                .favorableColor(dto.getFavorableColor())
                .koiFishOrigin(dto.getKoiFishOrigin())
                .koiFishDescription(dto.getKoiFishDescription())
                .koiFishPrice(dto.getKoiFishPrice())
                .build();
    }
}
