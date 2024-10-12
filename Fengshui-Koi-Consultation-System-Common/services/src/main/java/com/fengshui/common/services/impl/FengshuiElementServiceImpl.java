package com.fengshui.common.services.impl;

import com.fengshui.common.repository.postgresql.IFengshuiElementRepository;
import com.fengshui.common.repository.postgresql.IKoiFishRepository;
import com.fengshui.common.repository.postgresql.dto.FengshuiElementDTO;
import com.fengshui.common.repository.postgresql.dto.KoiFishDTO;
import com.fengshui.common.repository.postgresql.mapper.FengshuiElementMapper;
import com.fengshui.common.repository.postgresql.mapper.KoiFishMapper;
import com.fengshui.common.services.FengshuiElementService;
import com.fengshui.common.shared.Request.Element.GetFengshuiElementRequestModel;
import com.fengshui.common.shared.Request.FishPond.GetFishPondRequestModel;
import com.fengshui.common.shared.Response.Element.GetFengshuiElementResponseModel;
import com.fengshui.common.shared.Response.FishPond.GetFishPondResponseModel;
import com.fengshui.common.shared.Response.KoiFish.GetKoiFishResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FengshuiElementServiceImpl implements FengshuiElementService {

    @Autowired
    IFengshuiElementRepository fengshuiElementRepository;
    @Override
    public ResponseEntity<GetFengshuiElementResponseModel> getListFengshuiElement(GetFengshuiElementRequestModel requestModel) {
        GetFengshuiElementResponseModel response;
        List<FengshuiElementDTO> elementList = this.fengshuiElementRepository.findAll().stream().map(FengshuiElementMapper::toDTO).toList();
        response = new GetFengshuiElementResponseModel(false, elementList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
