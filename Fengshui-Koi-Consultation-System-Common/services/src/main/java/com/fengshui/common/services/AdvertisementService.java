package com.fengshui.common.services;

import com.fengshui.common.shared.Request.Advertisement.CreateAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.DeleteAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.GetListAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.UpdateAdvertisementRequestModel;
import com.fengshui.common.shared.Request.KoiFish.CreateKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.DeleteKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.GetKoiFishRequestModel;
import com.fengshui.common.shared.Request.KoiFish.UpdateKoiFishRequestModel;
import com.fengshui.common.shared.Response.Advertisement.CreateAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.DeleteAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.UpdateAdvertisementResponseModel;
import com.fengshui.common.shared.Response.KoiFish.CreateKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.DeleteKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.GetKoiFishResponseModel;
import com.fengshui.common.shared.Response.KoiFish.UpdateKoiFishResponseModel;
import org.springframework.http.ResponseEntity;

public interface AdvertisementService {
    public ResponseEntity<CreateAdvertisementResponseModel> createAdvertisement(CreateAdvertisementRequestModel requestModel);
    public ResponseEntity<GetListAdvertisementResponseModel> getListAdvertisement(GetListAdvertisementRequestModel requestModel);
    public ResponseEntity<UpdateAdvertisementResponseModel> updateAdvertisement(UpdateAdvertisementRequestModel requestModel);
    public ResponseEntity<DeleteAdvertisementResponseModel> deleteAdvertisement(DeleteAdvertisementRequestModel requestModel);
}
