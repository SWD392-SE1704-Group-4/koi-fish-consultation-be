package com.fengshui.common.aws.Cognito.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.fengshui.common.aws.Cognito.CognitoUserPool;
import com.fengshui.common.aws.Cognito.model.CognitoUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

public class CognitoUserPoolImpl implements CognitoUserPool {

    @Value("${cognitoProperties.userPoolId}")
    private String userPoolId;

    @Value("${cognitoProperties.clientId}")
    private String clientId;

    @Value("${cognitoProperties.clientSecret}")
    private String clientSecret;

    @Value("${amazonProperties.accessKey}")
    private String accessKeyId;

    @Value("${amazonProperties.secretKey}")
    private String secretAccessKey;

    private CognitoIdentityProviderClient cognitoClient;

    @PostConstruct
    private void initializeCognito() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        cognitoClient = CognitoIdentityProviderClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }


    @Override
    public SignUpResponse signUp(String username, String password, String email, String phoneNumber, String firstName, String lastName) {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .clientId(clientId)
                .username(username)
                .password(password)
                .userAttributes(
                        AttributeType.builder().name("email").value(email).build(),
                        AttributeType.builder().name("phone_number").value(phoneNumber).build(),
                        AttributeType.builder().name("given_name").value(firstName).build(),
                        AttributeType.builder().name("family_name").value(lastName).build()
                )
                .build();

        return cognitoClient.signUp(signUpRequest);
    }

    @Override
    public AdminUpdateUserAttributesResponse adminUpdateUser(String username, String email, String phoneNumber, String firstName, String lastName) {
        AdminUpdateUserAttributesRequest request = AdminUpdateUserAttributesRequest.builder()
                .userPoolId(userPoolId)
                .username(username)
                .userAttributes(
                        AttributeType.builder().name("email").value(email).build(),
                        AttributeType.builder().name("phone_number").value(phoneNumber).build(),
                        AttributeType.builder().name("given_name").value(firstName).build(),
                        AttributeType.builder().name("family_name").value(lastName).build()
                )
                .build();

        return cognitoClient.adminUpdateUserAttributes(request);
    }

    @Override
    public void updateUser(String accessToken, CognitoUser user) {
        String email = user.getEmail();
        String phoneNumber = user.getPhoneNumber();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String picture = user.getPicture();
        UpdateUserAttributesRequest request = UpdateUserAttributesRequest.builder()
                .accessToken(accessToken)
                .userAttributes(
                        AttributeType.builder().name("email").value(email).build(),
                        AttributeType.builder().name("phone_number").value(phoneNumber).build(),
                        AttributeType.builder().name("given_name").value(firstName).build(),
                        AttributeType.builder().name("family_name").value(lastName).build(),
                        AttributeType.builder().name("picture").value(picture).build()  // Optional, only if you have a picture attribute
                )
                .build();
        cognitoClient.updateUserAttributes(request);
    }

    @Override
    public boolean confirmSignUp(String username, String confirmationCode) {
        ConfirmSignUpRequest request = ConfirmSignUpRequest.builder()
                .clientId(clientId)
                .username(username)
                .confirmationCode(confirmationCode)
                .build();

        try {
            cognitoClient.confirmSignUp(request);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void resendConfirmationCode(String username) {
        ResendConfirmationCodeRequest request = ResendConfirmationCodeRequest.builder()
                .clientId(clientId)
                .username(username)
                .build();

        cognitoClient.resendConfirmationCode(request);
    }
}
