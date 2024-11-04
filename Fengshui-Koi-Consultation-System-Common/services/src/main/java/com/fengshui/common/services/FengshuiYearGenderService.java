package com.fengshui.common.services;

import com.fengshui.common.repository.postgresql.dto.FengShuiConsulationDTO;
import com.fengshui.common.shared.Request.HeavenlyEarthlyModel.CalcutateByGPTRequestModel;
import com.fengshui.common.shared.Response.HeavenlyEarthly.CalcutateByGPTResponseModel;
import org.springframework.http.ResponseEntity;

public interface FengshuiYearGenderService {

    ResponseEntity<FengShuiConsulationDTO> calculateFengShui(int year);
    ResponseEntity<CalcutateByGPTResponseModel> calculateFengShuiByGPT(CalcutateByGPTRequestModel data);
    String getFishRecommendation(String element);
    String getTankDirection(String element);
}
