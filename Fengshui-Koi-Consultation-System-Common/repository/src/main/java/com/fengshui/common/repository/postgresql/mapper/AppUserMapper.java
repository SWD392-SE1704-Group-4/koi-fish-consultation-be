package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.AppUserDTO;
import com.fengshui.common.repository.postgresql.entities.AppUserEntity;

public class AppUserMapper {
    // Convert from AppUserEntity to AppUserDTO
    public static AppUserDTO toDTO(AppUserEntity entity) {
        if (entity == null) {
            return null;
        }

        return AppUserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .emailVerified(entity.getEmailVerified())
                .phoneNumber(entity.getPhoneNumber())
                .phoneNumberVerified(entity.getPhoneNumberVerified())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthdate(entity.getBirthdate())
                .gender(entity.getGender())
                .address(entity.getAddress())
                .profilePictureUrl(entity.getProfilePictureUrl())
                .status(entity.getStatus())
                .role(entity.getRole())
                .lastLogin(entity.getLastLogin())
                .build();
    }

    // Convert from AppUserDTO to AppUserEntity
    public static AppUserEntity toEntity(AppUserDTO dto) {
        if (dto == null) {
            return null;
        }

        AppUserEntity entity = new AppUserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setEmailVerified(dto.getEmailVerified());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setPhoneNumberVerified(dto.getPhoneNumberVerified());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthdate(dto.getBirthdate());
        entity.setGender(dto.getGender());
        entity.setAddress(dto.getAddress());
        entity.setProfilePictureUrl(dto.getProfilePictureUrl());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        entity.setLastLogin(dto.getLastLogin());

        return entity;
    }
}
