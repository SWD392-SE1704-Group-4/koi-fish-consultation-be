package com.fengshui.common.repository.postgresql.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FengshuiDirectionDTO {
    private UUID directionId;
    private String directionName;
    private String description;
    private String elementAssociation;
    private String yinYangBalance;
    private String favorableAttributes;
    private String unfavorableAttributes;
    private String secondaryDirection;
    private String fengshuiTips;
    private String associatedColor;
    private String associatedShape;
    private FengshuiElementDTO fengshuiElement;
}
