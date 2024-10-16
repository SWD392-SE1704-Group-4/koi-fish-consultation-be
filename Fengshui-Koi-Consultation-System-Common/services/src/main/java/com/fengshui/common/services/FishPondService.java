package com.fengshui.common.services;

import com.fengshui.common.shared.Request.FishPond.CreateFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.GetFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.UpdateFishPondRequestModel;
import com.fengshui.common.shared.Response.FishPond.CreateFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.GetFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.UpdateFishPondResponseModel;
import org.springframework.http.ResponseEntity;

public interface FishPondService {
    public ResponseEntity<CreateFishPondResponseModel> createFishPond(CreateFishPondRequestModel requestModel);
    public ResponseEntity<GetFishPondResponseModel> getListFishPond(GetFishPondRequestModel requestModel);

    public ResponseEntity<UpdateFishPondResponseModel> updateFishPond(UpdateFishPondRequestModel requestModel);
}
