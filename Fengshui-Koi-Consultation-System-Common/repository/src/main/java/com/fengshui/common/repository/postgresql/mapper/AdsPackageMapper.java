package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.AdsPackageDTO;
import com.fengshui.common.repository.postgresql.entities.AdsPackageEntity;

public class AdsPackageMapper {

    public static AdsPackageDTO toDTO(AdsPackageEntity entity){
        if(entity == null){return null;}
        return AdsPackageDTO.builder()
                .adsPackageId(entity.getId())
                .adsPackageName(entity.getPackageName())
                .packagePrice(entity.getPackagePrice())
                .packageValue(entity.getPackageValue())
                .packagePriority(entity.getPackagePriority())
                .build();
    }

    public static AdsPackageEntity toEntity(AdsPackageDTO dto){
        if(dto == null){return null;}
        return AdsPackageEntity.builder()
                .id(dto.getAdsPackageId())
                .packageName(dto.getAdsPackageName())
                .packagePrice(dto.getPackagePrice())
                .packageValue(dto.getPackageValue())
                .packagePriority(dto.getPackagePriority())
                .build();
    }


}
