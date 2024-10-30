package com.fengshui.common.services.impl;


import com.fengshui.common.repository.postgresql.IFengshuiDirectionRepository;
import com.fengshui.common.repository.postgresql.dto.FengshuiDirectionDTO;
import com.fengshui.common.repository.postgresql.dto.FengshuiElementDTO;
import com.fengshui.common.repository.postgresql.mapper.FengshuiDirectionMapper;
import com.fengshui.common.repository.postgresql.mapper.FengshuiElementMapper;
import com.fengshui.common.services.FengshuiDirectionService;
import com.fengshui.common.shared.Request.Direction.GetFengshuiDirectionRequestModel;
import com.fengshui.common.shared.Response.Direction.GetFengshuiDirectionResponseModel;
import com.fengshui.common.shared.Response.Element.GetFengshuiElementResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FengshuiDirectionServiceImpl implements FengshuiDirectionService {
    @Autowired
    IFengshuiDirectionRepository fengshuiDirectionRepository;
    @Override
    public ResponseEntity<GetFengshuiDirectionResponseModel> getListFengshuiDirection(GetFengshuiDirectionRequestModel requestModel) {
        GetFengshuiDirectionResponseModel response;
        List<FengshuiDirectionDTO> elementList = this.fengshuiDirectionRepository.findAll().stream().map(FengshuiDirectionMapper::toDTO).toList();
        response = new GetFengshuiDirectionResponseModel(false, elementList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
