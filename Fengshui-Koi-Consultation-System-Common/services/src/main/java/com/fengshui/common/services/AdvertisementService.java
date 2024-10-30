package com.fengshui.common.services;

import com.fengshui.common.shared.Request.Advertisement.*;
import com.fengshui.common.shared.Request.KoiFish.CreateKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.DeleteKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.GetKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.UpdateKoiFishRequestModel;
import com.fengshui.common.shared.Response.Advertisement.*;
import com.fengshui.common.shared.Response.KoiFish.CreateKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.DeleteKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.GetKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.UpdateKoiFishResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdvertisementService {
    public ResponseEntity<CreateAdvertisementResponseModel> createAdvertisement(CreateAdvertisementRequestModel requestModel);
    public ResponseEntity<GetListAdvertisementResponseModel> getListAdvertisement(GetListAdvertisementRequestModel requestModel);
    public ResponseEntity<GetListAdvertisementResponseModel> getListAdvertisementByStaff(GetListAdvertisementRequestModel requestModel);
    public ResponseEntity<GetListAdvertisementByCreatorResponseModel> getListAdvertisementByCreator(GetListAdvertisementByCreatorRequestModel requestModel);
    public ResponseEntity<GetAdvertisementByIdResponseModel> getAdvertisementById(GetAdvertisementByIdRequestModel requestModel);
    public ResponseEntity<GetListAdvertisementResponseModel> getListAdvertisementType(GetListAdvertisementRequestModel requestModel);
    public ResponseEntity<UpdateAdvertisementResponseModel> updateAdvertisement(UpdateAdvertisementRequestModel requestModel);
    public ResponseEntity<DeleteAdvertisementResponseModel> deleteAdvertisement(DeleteAdvertisementRequestModel requestModel);
    public ResponseEntity<VerifyAdvertisementResponseModel> verifyAdvertisement(VerifyAdvertisementRequestModel requestModel);
    public ResponseEntity<DenyAdvertisementResponseModel> denyAdvertisement(DenyAdvertisementRequestModel requestModel);
}
