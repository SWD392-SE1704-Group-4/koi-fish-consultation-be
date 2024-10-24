package com.fengshui.common.services.impl;

import com.fengshui.common.aws.Cognito.CognitoUserPool;
import com.fengshui.common.services.AppUserService;
import com.fengshui.common.shared.Request.AppUser.GetAppUserGroupRequestModel;
import com.fengshui.common.shared.Response.AppUser.GetAppUserGroupResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminListGroupsForUserRequest;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements AppUserService {

    @Autowired
    CognitoUserPool cognitoUserPool;
    @Override
    public ResponseEntity<GetAppUserGroupResponseModel> getAppUserGroup(GetAppUserGroupRequestModel requestModel) {
        GetAppUserGroupResponseModel response;
        try {
            List<String> groupNames = cognitoUserPool.getUserGroups(requestModel.getUserName());
            response = new GetAppUserGroupResponseModel(false, groupNames, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = new GetAppUserGroupResponseModel(true, null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
