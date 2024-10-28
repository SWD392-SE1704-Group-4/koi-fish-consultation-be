package com.fengshui.common.repository.postgresql.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class FishPondDTO {
    private UUID pondId;
    private String pondName;
    private String pondShape;
    private Double pondSize;
    private Double pondDepth;
    private String pondMaterial;
    private Boolean hasWaterfall;
    private Boolean hasPlants;
    private Boolean hasRocks;
    private Boolean isSaltwater;
    private Integer numKoiFish;
    private Double waterCapacity;
    private String pondLocation;
    private List<String> pondPictures;
    private FengshuiDirectionDTO pondOrientation;
    private AppUserDTO createBy;
}
