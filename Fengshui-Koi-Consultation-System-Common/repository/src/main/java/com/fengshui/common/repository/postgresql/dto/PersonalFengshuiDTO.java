package com.fengshui.common.repository.postgresql.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonalFengshuiDTO {
    private String id;
    private String earthlyBranch;
    private String element;
    private String fishRecommendation;
    private String heavenlyStem;
    private String tankDirection;
    private String appUserId;
}
