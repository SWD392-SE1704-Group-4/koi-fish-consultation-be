package com.fengshui.common.services.impl;

import com.fengshui.common.services.FishPondService;
import com.fengshui.common.shared.Request.FishPond.CreateFishPondRequestModel;
import com.fengshui.common.shared.Request.FishPond.GetFishPondRequestModel;
import com.fengshui.common.shared.Response.FishPond.CreateFishPondResponseModel;
import com.fengshui.common.shared.Response.FishPond.GetFishPondResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class FishPondServiceImpl implements FishPondService {
    @Override
    public ResponseEntity<CreateFishPondResponseModel> createFishPond(CreateFishPondRequestModel requestModel) {
        return null;
    }

    @Override
    public ResponseEntity<GetFishPondResponseModel> getListFishPond(GetFishPondRequestModel requestModel) {
        return null;
    }
}
