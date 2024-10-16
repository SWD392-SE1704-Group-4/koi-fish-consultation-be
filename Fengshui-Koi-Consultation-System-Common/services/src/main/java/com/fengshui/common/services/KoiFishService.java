package com.fengshui.common.services;

import com.fengshui.common.repository.postgresql.entities.KoiFishEntity;
import com.fengshui.common.shared.Request.KoiFish.CreateKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.DeleteKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.GetKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.UpdateKoiFishRequestModel;
import com.fengshui.common.shared.Response.BaseResponseModel;
import com.fengshui.common.shared.Response.KoiFish.CreateKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.DeleteKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.GetKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.UpdateKoiFishResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface KoiFishService {
    public ResponseEntity<CreateKoiFishResponseModel> createKoiFish(CreateKoiFishRequestModel requestModel);
    public ResponseEntity<GetKoiFishResponseModel> getListKoiFish(GetKoiFishRequestModel requestModel);
    public ResponseEntity<UpdateKoiFishResponseModel> updateKoiFish(UpdateKoiFishRequestModel requestModel);
    public ResponseEntity<DeleteKoiFishResponseModel> deleteKoiFish(DeleteKoiFishRequestModel requestModel);
}
