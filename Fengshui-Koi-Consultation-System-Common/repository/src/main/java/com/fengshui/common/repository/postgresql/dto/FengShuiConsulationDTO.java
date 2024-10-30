package com.fengshui.common.repository.postgresql.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FengShuiConsulationDTO {
    private String heavenlyStem;
    private String earthlyBranch;
    private String element;
    private String fishRecommendation;
    private String tankDirection;

    public FengShuiConsulationDTO(String heavenlyStem, String earthlyBranch, String element, String fishRecommendation, String tankDirection) {
        this.heavenlyStem = heavenlyStem;
        this.earthlyBranch = earthlyBranch;
        this.element = element;
        this.fishRecommendation = fishRecommendation;
        this.tankDirection = tankDirection;
    }
}
