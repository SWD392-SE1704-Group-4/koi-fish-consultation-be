package com.fengshui.common.services;

import com.fengshui.common.repository.postgresql.dto.FengShuiConsulationDTO;
import org.springframework.http.ResponseEntity;

public interface FengshuiYearGenderService {

    ResponseEntity<FengShuiConsulationDTO> calculateFengShui(int year);

    String getFishRecommendation(String element);
    String getTankDirection(String element);
}
