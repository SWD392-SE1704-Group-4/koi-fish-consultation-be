package com.fengshui.common.aws.Cognito;

import com.fengshui.common.aws.Cognito.model.CognitoUser;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

import java.security.PublicKey;
import java.util.List;

public interface CognitoUserPool {
    SignUpResponse signUp(String username, String password, String email, String phoneNumber, String firstName, String lastName);
    AdminUpdateUserAttributesResponse adminUpdateUser(String username, String email, String phoneNumber, String firstName, String lastName);
    void updateUser(String accessToken, CognitoUser user);
    boolean confirmSignUp(String username, String confirmationCode);
    void resendConfirmationCode(String username);
    public AdminGetUserResponse getUserById(String username);
    public List<String> getUserGroups(String username);
    public PublicKey getPublicKeyFromJwk(String token) throws Exception;
    public AdminInitiateAuthResponse loginAppUser(String email, String password);
}
