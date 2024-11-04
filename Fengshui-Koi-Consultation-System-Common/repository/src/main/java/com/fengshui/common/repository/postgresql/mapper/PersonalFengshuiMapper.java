package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.PersonalFengshuiDTO;
import com.fengshui.common.repository.postgresql.entities.PersonalFengshuiEntity;

public class PersonalFengshuiMapper {
    // Converts a PersonalFengshuiEntity to a PersonalFengshuiDTO
    public static PersonalFengshuiDTO toDTO(PersonalFengshuiEntity entity) {
        if (entity == null) {
            return null;
        }
        return PersonalFengshuiDTO.builder()
                .id(entity.getId().toString())
                .earthlyBranch(entity.getEarthlyBranch())
                .element(entity.getElement())
                .fishRecommendation(entity.getFishRecommendation())
                .heavenlyStem(entity.getHeavenlyStem())
                .tankDirection(entity.getTankDirection())
                .appUserId(entity.getAppUser() != null ? entity.getAppUser().getId().toString() : null)
                .build();
    }
}
