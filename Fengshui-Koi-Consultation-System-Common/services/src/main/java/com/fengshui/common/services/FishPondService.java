package com.fengshui.common.services;

import com.fengshui.common.shared.Request.FishPond.*;
import com.fengshui.common.shared.Response.FishPond.*;
import org.springframework.http.ResponseEntity;

public interface FishPondService {
    public ResponseEntity<CreateFishPondResponseModel> createFishPond(CreateFishPondRequestModel requestModel);
    public ResponseEntity<GetFishPondResponseModel> getListFishPond(GetFishPondRequestModel requestModel);
    public ResponseEntity<GetFishPondByUserResponseModel> getListFishPondByCreator(GetFishPondByUserRequestModel requestModel);
    public ResponseEntity<UpdateFishPondResponseModel> updateFishPond(UpdateFishPondRequestModel requestModel);
    public ResponseEntity<DeleteFishPondResponseModel> deleteFishPond(DeleteFishPondRequestModel requestModel);
}
