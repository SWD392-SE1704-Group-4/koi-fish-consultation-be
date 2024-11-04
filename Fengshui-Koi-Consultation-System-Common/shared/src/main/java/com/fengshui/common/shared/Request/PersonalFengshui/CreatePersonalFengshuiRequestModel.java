package com.fengshui.common.shared.Request.PersonalFengshui;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePersonalFengshuiRequestModel {
    private UUID appUserId;
    private String earthlyBranch;
    private String element;
    private String fishRecommendation;
    private String heavenlyStem;
    private String tankDirection;
}
