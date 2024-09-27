package com.fengshui.common.services.impl;

import com.fengshui.common.repository.postgresql.IKoiFishRepository;
import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import com.fengshui.common.services.KoiFishService;
import com.fengshui.common.shared.Request.KoiFish.CreateKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.GetKoiFishRequestModel;
import com.fengshui.common.shared.Response.BaseResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KoiFishServiceImpl implements KoiFishService {

    @Autowired
    IKoiFishRepository koiFishRepository;

    @Override
    @Transactional
    public BaseResponseModel createKoiFish(CreateKoiFishRequestModel requestModel) {
        KoiFishEntity koiFish = KoiFishEntity.builder()
                .koiFishName(requestModel.getKoiFishName())
                .koiFishColor(requestModel.getKoiFishColor())
                .koiFishSize(requestModel.getKoiFishSize())
                .koiFishAge(requestModel.getKoiFishAge())
                .koiFishPicture(requestModel.getKoiFishPicture())
                .build();
        KoiFishEntity newKoiFish = koiFishRepository.save(koiFish);
        return BaseResponseModel.builder()
                .has_error(false)
                .payload(newKoiFish)
                .error(null)
                .build();
    }

    @Override
    public BaseResponseModel getListKoiFish(GetKoiFishRequestModel requestModel) {
        List<KoiFishEntity> koiList = this.koiFishRepository.findAll();
        return BaseResponseModel.builder()
                .has_error(false)
                .payload(koiList)
                .error(null)
                .build();
    }
}
