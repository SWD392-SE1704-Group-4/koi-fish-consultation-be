package com.fengshui.common.repository.postgresql.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class AdvertisementDTO {
    private UUID advertisementId;
    private String title;
    private String description;
    private String location;
    private String contactInfo;
    private String advertisementType;
    private int quantity;
    private int viewsCount;
    private String status;
    private boolean adminVerified;
    private LocalDateTime expirationDate;
    private KoiFishDTO koiFish;
    private String koiFishName;
    private String postedBy;
    private Object userInfo;
    private List<String> additionalImages;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

