package com.fengshui.common.services.impl;

import com.fengshui.common.repository.postgresql.IHeavenlyEarthlyRepository;
import com.fengshui.common.repository.postgresql.dto.HeavenlyEarthlyDTO;
import com.fengshui.common.repository.postgresql.mapper.HeavenlyEarthlyMapper;
import com.fengshui.common.services.HeavenlyEarthlyService;
import com.fengshui.common.shared.Request.HeavenlyEarthlyModel.GetHeavenlyEarthlyRequestModel;
import com.fengshui.common.shared.Response.HeavenlyEarthly.GetHeavenlyEarthlyResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@ComponentScan(basePackages = "com.fengshui.common")
public class HeavenlyEarthlyServiceImpl implements HeavenlyEarthlyService {
    @Autowired
    IHeavenlyEarthlyRepository heavenlyEarthlyElementRepository;

    @Override
    public ResponseEntity<GetHeavenlyEarthlyResponseModel> getListHeavenlyEarthlyElement(GetHeavenlyEarthlyRequestModel requestModel) {
        GetHeavenlyEarthlyResponseModel response;
        try {
            List<HeavenlyEarthlyDTO> elementList = heavenlyEarthlyElementRepository.findAll()
                    .stream()
                    .map(HeavenlyEarthlyMapper::toDTO)
                    .collect(Collectors.toList());

            response = new GetHeavenlyEarthlyResponseModel(false, elementList, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception error) {
            response = new GetHeavenlyEarthlyResponseModel(true, null, error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
