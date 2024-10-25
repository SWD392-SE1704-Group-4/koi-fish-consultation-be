package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.TransactionDTO;
import com.fengshui.common.repository.postgresql.entities.TransactionEntity;

import java.time.LocalDateTime;

public class TransactionMapper {
    public static TransactionDTO toDTO(TransactionEntity entity){
        if (entity == null) {return null;
    }
    return TransactionDTO.builder()
            .id(entity.getId())
            .createdAt(LocalDateTime.parse(entity.getCreatedAt().toString()))
            .userId(entity.getUserId())
            .adsPackage(entity.getAdsPackage())
            .build();
}


public static TransationEntity toEntity(TransactionDTO dto) {
    if (dto == null) return null;
}
return TransactionEntity.builder()
