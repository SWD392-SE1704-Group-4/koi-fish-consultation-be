package com.fengshui.common.services.impl;

import com.fengshui.common.aws.Cognito.CognitoUserPool;
import com.fengshui.common.repository.postgresql.IAppUserRepository;
import com.fengshui.common.repository.postgresql.dto.AdvertisementPackageDTO;
import com.fengshui.common.repository.postgresql.dto.AppUserDTO;
import com.fengshui.common.repository.postgresql.dto.AppUserLoginDTO;
import com.fengshui.common.repository.postgresql.entities.AdvertisementPackageEntity;
import com.fengshui.common.repository.postgresql.entities.AppUserEntity;
import com.fengshui.common.repository.postgresql.mapper.AdvertisementPackageMapper;
import com.fengshui.common.repository.postgresql.mapper.AppUserMapper;
import com.fengshui.common.services.AppUserService;
import com.fengshui.common.shared.Request.AdsPackage.GetPackageByUserIdRequestModel;
import com.fengshui.common.shared.Request.AppUser.*;
import com.fengshui.common.shared.Response.AdsPackage.GetPackageByUserIdResponseModel;
import com.fengshui.common.shared.Response.AppUser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    CognitoUserPool cognitoUserPool;

    @Autowired
    IAppUserRepository appUserRepository;
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

    @Override
    public ResponseEntity<GetAppUserRoleResponseModel> getAppUserRole(GetAppUserRoleRequestModel requestModel) {
        GetAppUserRoleResponseModel response;
        Optional<AppUserEntity> optionalUser = this.appUserRepository.findById(requestModel.getAppUserId());

        if (optionalUser.isEmpty()) {
            response = new GetAppUserRoleResponseModel(true, null, "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        AppUserEntity appUser = optionalUser.get();
        AppUserDTO dto = AppUserMapper.toDTO(appUser);

        response = new GetAppUserRoleResponseModel(false, dto.getRole(), null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<GetAppUserByIdResponseModel> getAppUserById(GetAppUserByIdRequestModel requestModel) {
        GetAppUserByIdResponseModel response;
        Optional<AppUserEntity> optionalUser = this.appUserRepository.findById(requestModel.getAppUserId());

        if (optionalUser.isEmpty()) {
            response = new GetAppUserByIdResponseModel(true, null, "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        AppUserEntity appUser = optionalUser.get();
        AppUserDTO dto = AppUserMapper.toDTO(appUser);

        response = new GetAppUserByIdResponseModel(false, dto, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<GetPackageByUserIdResponseModel> getAppUserPackage(GetPackageByUserIdRequestModel requestModel) {
        GetPackageByUserIdResponseModel response;

        Optional<AppUserEntity> optionalUser = this.appUserRepository.findById(requestModel.getAppUserId());
        if (optionalUser.isEmpty()) {
            response = new GetPackageByUserIdResponseModel(true, null, "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        AppUserEntity appUser = optionalUser.get();
        AdvertisementPackageEntity currentPackage = appUser.getCurrentPackage();

        if (currentPackage == null) {
            response = new GetPackageByUserIdResponseModel(true, null, "No active package for user.");
            return ResponseEntity.status(HttpStatus.OK).body(response);  // or HttpStatus.NOT_FOUND if preferred
        }

        AdvertisementPackageDTO packageDTO = AdvertisementPackageMapper.toDTO(currentPackage);

        response = new GetPackageByUserIdResponseModel(false, packageDTO, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Object> changePassword(Object requestModel) {
        return null;
    }

    @Override
    public ResponseEntity<Object> verifyEmail(Object requestModel) {
        return null;
    }

    @Override
    public ResponseEntity<AppUserLoginResponseModel> loginAppUser(AppUserLoginRequestModel requestModel) {
        AppUserLoginResponseModel response;
        try {
            AdminInitiateAuthResponse authResponse = cognitoUserPool.loginAppUser(requestModel.getEmail(), requestModel.getPassword());

            String accessToken = authResponse.authenticationResult().accessToken();
            String idToken = authResponse.authenticationResult().idToken();
            String refreshToken = authResponse.authenticationResult().refreshToken();

            AppUserLoginDTO loginDTO = new AppUserLoginDTO(accessToken, idToken, refreshToken);

            response = new AppUserLoginResponseModel(false, loginDTO, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            response = new AppUserLoginResponseModel(true, null, "Invalid email or password.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    public ResponseEntity<GetAppUserResponseModel> getAppUser(GetAppUserRequestModel requestModel) {
        List<AppUserDTO> users = appUserRepository.findAll()
                .stream()
                .map(AppUserMapper::toDTO) // Map each AppUserEntity to AppUserDTO
                .collect(Collectors.toList());

        GetAppUserResponseModel response = new GetAppUserResponseModel(false, users, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
