package com.fengshui.common.aws.Cognito;

import com.fengshui.common.aws.Cognito.model.CognitoUser;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

public interface CognitoUserPool {
    SignUpResponse signUp(String username, String password, String email, String phoneNumber, String firstName, String lastName);
    AdminUpdateUserAttributesResponse adminUpdateUser(String username, String email, String phoneNumber, String firstName, String lastName);
    void updateUser(String accessToken, CognitoUser user);
    boolean confirmSignUp(String username, String confirmationCode);
    void resendConfirmationCode(String username);
}
