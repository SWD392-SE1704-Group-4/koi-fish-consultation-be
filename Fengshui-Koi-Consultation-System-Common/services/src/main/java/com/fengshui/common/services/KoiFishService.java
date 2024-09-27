package com.fengshui.common.services;

import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import com.fengshui.common.shared.Request.KoiFish.CreateKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.GetKoiFishRequestModel;
import com.fengshui.common.shared.Response.BaseResponseModel;
import org.springframework.stereotype.Component;

import java.util.List;

public interface KoiFishService {
    public BaseResponseModel createKoiFish(CreateKoiFishRequestModel requestModel);
    public BaseResponseModel getListKoiFish(GetKoiFishRequestModel requestModel);
}
