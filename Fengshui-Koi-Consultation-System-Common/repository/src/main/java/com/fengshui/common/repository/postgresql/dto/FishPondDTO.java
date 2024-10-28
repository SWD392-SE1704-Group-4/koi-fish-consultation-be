package com.fengshui.common.repository.postgresql.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class FishPondDTO {
    private UUID pondId;
    private String pondName;
    private String pondShape;
    private Double pondSize;  // in square meters
    private Double pondDepth;  // in meters
    private String pondMaterial;  // Material like stone, concrete, etc.
    private Boolean hasWaterfall;
    private Boolean hasPlants;
    private Boolean hasRocks;
    private Boolean isSaltwater;
    private Integer numKoiFish;
    private Double waterCapacity;  // in liters or cubic meters
    private String pondElement;   // Feng Shui element associated with the pond (e.g., Water, Earth, etc.)
    private String pondLocation;  // Indoor or outdoor
    private String pondOrientation;  // Direction the pond is facing
}
