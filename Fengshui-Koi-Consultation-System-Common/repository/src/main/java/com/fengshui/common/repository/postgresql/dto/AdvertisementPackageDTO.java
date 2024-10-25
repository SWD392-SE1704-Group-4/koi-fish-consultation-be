package com.fengshui.common.repository.postgresql.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class AdvertisementPackageDTO {
    private UUID packageId;
    private String packageName;
    private String description;
    private BigDecimal price;
    private Integer durationInDays;   // Duration of the advertisement package in days
    private Integer maxAds;           // Maximum number of ads allowed in the package
    private Boolean isActive;         // If the package is currently active
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
