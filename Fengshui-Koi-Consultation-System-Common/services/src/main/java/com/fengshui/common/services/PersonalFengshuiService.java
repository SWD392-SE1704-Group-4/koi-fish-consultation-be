package com.fengshui.common.services;

import com.fengshui.common.shared.Request.KoiFish.CreateKoiFishRequestModel;
import com.fengshui.common.shared.Request.PersonalFengshui.CreatePersonalFengshuiRequestModel;
import com.fengshui.common.shared.Request.PersonalFengshui.GetPersonalFengshuiResponseModel;
import com.fengshui.common.shared.Response.KoiFish.CreateKoiFishResponseModel;
import com.fengshui.common.shared.Response.PersonalFengshui.CreatePersonalFengshuiResponseModel;
import com.fengshui.common.shared.Response.PersonalFengshui.GetPersonalFengshuiRequestModel;
import org.springframework.http.ResponseEntity;

public interface PersonalFengshuiService {
    public ResponseEntity<CreatePersonalFengshuiResponseModel> createPersonalFengshui(CreatePersonalFengshuiRequestModel requestModel);
    public ResponseEntity<GetPersonalFengshuiResponseModel> getPersonalFengshui(GetPersonalFengshuiRequestModel requestModel);
}
