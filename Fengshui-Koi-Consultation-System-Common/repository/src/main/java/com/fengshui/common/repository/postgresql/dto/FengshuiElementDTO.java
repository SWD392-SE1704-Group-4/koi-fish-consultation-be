package com.fengshui.common.repository.postgresql.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class FengshuiElementDTO {
    private UUID elementId;
    private String elementName;
    private String elementColor;
    private String elementDirection;
    private String elementSeason;
    private String elementYinYang;
    private String elementAssociatedAnimals;
    private Integer elementStrength;
    private String createdAt;
    private String updatedAt;
}
