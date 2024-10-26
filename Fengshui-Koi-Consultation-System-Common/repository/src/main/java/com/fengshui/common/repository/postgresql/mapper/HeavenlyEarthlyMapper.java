package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.HeavenlyEarthlyDTO;
import com.fengshui.common.repository.postgresql.entities.HeavenlyEarthlyEntity;

public class HeavenlyEarthlyMapper {
    // Convert from HeavenlyEarthlyElementEntity to HeavenlyEarthlyDTO
    public static HeavenlyEarthlyDTO toDTO(HeavenlyEarthlyEntity entity) {
        if (entity == null) {
            return null;
        }
        return HeavenlyEarthlyDTO.builder()
                .heavenlyStem(entity.getHeavenlyStem())
                .earthlyBranch(entity.getEarthlyBranch())
                .fiveElement(entity.getFiveElement())
                .build();
    }

    // Convert from HeavenlyEarthlyDTO to HeavenlyEarthlyElementEntity
    public static HeavenlyEarthlyEntity toEntity(HeavenlyEarthlyDTO dto) {
        if (dto == null) {
            return null;
        }
        return HeavenlyEarthlyEntity.builder()
                .heavenlyStem(dto.getHeavenlyStem())
                .earthlyBranch(dto.getEarthlyBranch())
                .fiveElement(dto.getFiveElement())
                .build();
    }
}
