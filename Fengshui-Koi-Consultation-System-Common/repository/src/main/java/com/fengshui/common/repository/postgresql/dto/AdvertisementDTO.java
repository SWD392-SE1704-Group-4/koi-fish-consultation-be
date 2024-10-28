package com.fengshui.common.repository.postgresql.dto;

import com.fengshui.common.repository.postgresql.enums.AdvertisementType;
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
    private AdvertisementTypeDTO advertisementType;

    private String location;
    private String contactInfo;
    private String phone;
    private String address;

    private boolean verified;
    private String status;

    private int viewsCount;

    private KoiFishDTO koiFish;
    private FishPondDTO fishPond;

    private AppUserDTO postedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<String> additionalImages;
    private List<String> tags;
    private LocalDateTime expirationDate;
}

