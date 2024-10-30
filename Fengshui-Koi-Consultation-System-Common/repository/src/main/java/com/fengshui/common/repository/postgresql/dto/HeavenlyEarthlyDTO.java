package com.fengshui.common.repository.postgresql.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HeavenlyEarthlyDTO {
    private String heavenlyStem;
    private String earthlyBranch;
    private String fiveElement;
}
