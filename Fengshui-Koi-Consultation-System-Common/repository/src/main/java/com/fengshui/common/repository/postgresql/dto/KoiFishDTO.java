package com.fengshui.common.repository.postgresql.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class KoiFishDTO {
    private UUID id;
    private String koiFishName;
    private String koiFishColor;
    private double koiFishSize;
    private double koiFishAge;
    private List<String> koiFishPictures;
    private FengshuiElementDTO fengshuiElement;
    private String symbolicMeaning;
    private String energyType;
    private int favorableNumber;
    private String favorableColor;
    private String koiFishOrigin;
    private String koiFishDescription;
    private double koiFishPrice;
}

