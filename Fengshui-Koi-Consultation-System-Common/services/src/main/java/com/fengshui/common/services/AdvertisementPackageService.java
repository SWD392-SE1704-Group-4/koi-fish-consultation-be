package com.fengshui.common.services;

import com.fengshui.common.shared.Request.Advertisement.DeleteAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.GetAdvertisementByIdRequestModel;
import com.fengshui.common.shared.Request.Advertisement.GetListAdvertisementRequestModel;
import com.fengshui.common.shared.Request.Advertisement.UpdateAdvertisementRequestModel;
import com.fengshui.common.shared.Request.AdvertisementPackage.*;
import com.fengshui.common.shared.Response.Advertisement.DeleteAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetAdvertisementByIdResponseModel;
import com.fengshui.common.shared.Response.Advertisement.GetListAdvertisementResponseModel;
import com.fengshui.common.shared.Response.Advertisement.UpdateAdvertisementResponseModel;
import com.fengshui.common.shared.Response.AdvertisementPackage.*;
import org.springframework.http.ResponseEntity;

public interface AdvertisementPackageService {
    public ResponseEntity<CreateAdvertisementPackageResponseModel>createAdvertisementPackage(CreateAdvertisementPackageRequestModel requestModel);
    public ResponseEntity<GetListAdvertisementPackageResponseModel> getListAdvertisementPackage(GetListAdvertisementPackageRequestModel requestModel);
    public ResponseEntity<GetAdvertisementPackageByIdResponseModel> getAdvertisementPackageById(GetAdvertisementPackageByIdRequestModel requestModel);
    public ResponseEntity<UpdateAdvertisementPackageResponseModel> updateAdvertisementPackage(UpdateAdvertisementPackageRequestModel requestModel);
    public ResponseEntity<DeleteAdvertisementPackageResponseModel> deleteAdvertisementPackage(DeleteAdvertisementPackageRequestModel requestModel);
}
