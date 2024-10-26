package com.fengshui.common.repository.postgresql.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FengshuiYearGenderDTO {
    private int year;
    private String gender;


}
