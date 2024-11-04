package com.fengshui.common.services;

import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import com.fengshui.common.shared.Request.KoiFish.*;
import com.fengshui.common.shared.Response.BaseResponseModel;
import com.fengshui.common.shared.Response.KoiFish.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface KoiFishService {
    public ResponseEntity<CreateKoiFishResponseModel> createKoiFish(CreateKoiFishRequestModel requestModel);
    public ResponseEntity<GetKoiFishResponseModel> getListKoiFish(GetKoiFishRequestModel requestModel);
    public ResponseEntity<UpdateKoiFishResponseModel> updateKoiFish(UpdateKoiFishRequestModel requestModel);
    public ResponseEntity<DeleteKoiFishResponseModel> deleteKoiFish(DeleteKoiFishRequestModel requestModel);
    public ResponseEntity<GetKoiFishByElementNameResponseModel> getListKoiFishByElementName(GetKoiFishByElementNameRequestModel requestModel);
}
