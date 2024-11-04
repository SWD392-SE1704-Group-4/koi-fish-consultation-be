package com.fengshui.common.services.impl;

import com.fengshui.common.repository.postgresql.IAppUserRepository;
import com.fengshui.common.repository.postgresql.IPersonalFengshuiRepository;
import com.fengshui.common.repository.postgresql.entities.AppUserEntity;
import com.fengshui.common.repository.postgresql.entities.PersonalFengshuiEntity;
import com.fengshui.common.repository.postgresql.mapper.PersonalFengshuiMapper;
import com.fengshui.common.services.PersonalFengshuiService;
import com.fengshui.common.shared.Request.PersonalFengshui.CreatePersonalFengshuiRequestModel;
import com.fengshui.common.shared.Request.PersonalFengshui.GetPersonalFengshuiResponseModel;
import com.fengshui.common.shared.Response.PersonalFengshui.CreatePersonalFengshuiResponseModel;
import com.fengshui.common.shared.Response.PersonalFengshui.GetPersonalFengshuiRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonalFengshuiServiceImpl implements PersonalFengshuiService {
    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private IPersonalFengshuiRepository personalFengshuiRepository;
    @Override
    public ResponseEntity<CreatePersonalFengshuiResponseModel> createPersonalFengshui(CreatePersonalFengshuiRequestModel requestModel) {
        CreatePersonalFengshuiResponseModel response;
        try {
            // Retrieve the AppUserEntity by appUserId from the request model
            AppUserEntity appUser = appUserRepository.findById(requestModel.getAppUserId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid App User ID"));

            // Check if a PersonalFengshuiEntity already exists for the user
            PersonalFengshuiEntity personalFengshui = personalFengshuiRepository.findByAppUser(appUser)
                    .orElse(PersonalFengshuiEntity.builder().appUser(appUser).build());

            // Update fields from request model
            personalFengshui.setEarthlyBranch(requestModel.getEarthlyBranch());
            personalFengshui.setElement(requestModel.getElement());
            personalFengshui.setFishRecommendation(requestModel.getFishRecommendation());
            personalFengshui.setHeavenlyStem(requestModel.getHeavenlyStem());
            personalFengshui.setTankDirection(requestModel.getTankDirection());
            personalFengshui.setAppUser(appUser);
            // Save (or update) the entity in the database
            PersonalFengshuiEntity savedEntity = personalFengshuiRepository.save(personalFengshui);

            // Convert saved entity to DTO for the response model
            response = new CreatePersonalFengshuiResponseModel(
                    false,
                    PersonalFengshuiMapper.toDTO(savedEntity),
                    null
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            response = new CreatePersonalFengshuiResponseModel(
                    true, null, e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<GetPersonalFengshuiResponseModel> getPersonalFengshui(GetPersonalFengshuiRequestModel requestModel) {
        GetPersonalFengshuiResponseModel response;
        try {
            // Retrieve the AppUserEntity by appUserId from the request model
            AppUserEntity appUser = appUserRepository.findById(requestModel.getAppUserId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid App User ID"));

            // Retrieve the PersonalFengshuiEntity for the user
            PersonalFengshuiEntity personalFengshui = personalFengshuiRepository.findByAppUser(appUser)
                    .orElseThrow(() -> new IllegalArgumentException("Personal Feng Shui not found for the given user."));

            // Convert the found entity to DTO for the response model
            response = new GetPersonalFengshuiResponseModel(
                    false,
                    PersonalFengshuiMapper.toDTO(personalFengshui),
                    null
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IllegalArgumentException e) {
            // Return NOT FOUND if personal Feng Shui info does not exist for the user
            response = new GetPersonalFengshuiResponseModel(true, null, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            // Handle other unexpected errors
            response = new GetPersonalFengshuiResponseModel(true, null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
