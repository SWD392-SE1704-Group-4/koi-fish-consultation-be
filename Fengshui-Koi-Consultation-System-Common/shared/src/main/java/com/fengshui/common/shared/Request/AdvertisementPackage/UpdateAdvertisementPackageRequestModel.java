package com.fengshui.common.shared.Request.AdvertisementPackage;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UpdateAdvertisementPackageRequestModel implements Serializable {
    private UUID PackageId;
    private String packageName;
    private String description;
    private BigDecimal price;
    private int durationInDays;   // Duration of the advertisement package in days
    private int maxAds;           // Maximum number of ads allowed in the package
    private boolean isActive;         // If the package is currently active
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
