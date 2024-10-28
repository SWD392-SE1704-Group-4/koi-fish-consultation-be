package com.fengshui.common.shared.Request.AdvertisementPackage;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateAdvertisementPackageRequestModel {
    private String packageName;
    private String description;
    private BigDecimal price;
    private int durationInDays;
    private int maxAds;
    private Boolean isActive;

}
