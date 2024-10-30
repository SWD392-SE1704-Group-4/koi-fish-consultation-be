package com.fengshui.common.repository.postgresql.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AdsPackageDTO {
    private UUID adsPackageId;
    private String adsPackageName;
    private double packagePrice;
    private double packageValue;
    private int packagePriority;
}
