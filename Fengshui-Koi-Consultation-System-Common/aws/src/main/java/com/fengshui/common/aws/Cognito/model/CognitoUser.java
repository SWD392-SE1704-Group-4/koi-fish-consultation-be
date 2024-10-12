package com.fengshui.common.aws.Cognito.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CognitoUser {
    private UUID id;
    private String username;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String picture;
    private boolean emailVerified;
    private boolean phoneNumberVerified;
    private String status;
    private String accessToken;
    private String refreshToken;

}
