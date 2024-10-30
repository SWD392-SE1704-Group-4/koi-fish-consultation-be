package com.fengshui.common.aws.Cognito.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fengshui.common.aws.Cognito.CognitoUserPool;
import com.fengshui.common.aws.Cognito.model.CognitoUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CognitoUserPoolImpl implements CognitoUserPool {
    @Value("${cognitoProperties.jwks}")
    private String jwksUrl;

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
    private String calculateSecretHash(String clientId, String clientSecret, String username) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(clientSecret.getBytes("UTF-8"), "HmacSHA256");
            mac.init(secretKey);
            byte[] hmac = mac.doFinal((username + clientId).getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating SECRET_HASH", e);
        }
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
    @Override
    public AdminGetUserResponse getUserById(String username) {
        AdminGetUserRequest request = AdminGetUserRequest.builder()
                .userPoolId(userPoolId)
                .username(username)
                .build();

        return cognitoClient.adminGetUser(request);
    }

    @Override
    public List<String> getUserGroups(String username) {
        AdminListGroupsForUserRequest request = AdminListGroupsForUserRequest.builder()
                .userPoolId(userPoolId)
                .username(username)
                .build();

        AdminListGroupsForUserResponse response = cognitoClient.adminListGroupsForUser(request);

        // Extract group names from the response
        return response.groups().stream()
                .map(GroupType::groupName)
                .collect(Collectors.toList());
    }
    @Override
    public AdminInitiateAuthResponse loginAppUser(String email, String password) {
        String secretHash = calculateSecretHash(clientId, clientSecret, email);
        AdminInitiateAuthRequest authRequest = AdminInitiateAuthRequest.builder()
                .userPoolId(userPoolId)
                .clientId(clientId)
                .authFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .authParameters(Map.of(
                        "USERNAME", email,
                        "PASSWORD", password,
                        "SECRET_HASH", secretHash  // Thêm SECRET_HASH vào authParameters
                ))
                .build();

        return cognitoClient.adminInitiateAuth(authRequest);
    }

    public PublicKey getPublicKeyFromJwk(String token) throws Exception {
        String[] tokenParts = token.split("\\.");
        if (tokenParts.length != 3) {
            throw new IllegalArgumentException("Invalid JWT token format");
        }
        String headerJson = new String(Base64.getUrlDecoder().decode(tokenParts[0]));
        Map<String, Object> header = new ObjectMapper().readValue(headerJson, Map.class);
        String kid = (String) header.get("kid");
        if (kid == null) {
            throw new IllegalArgumentException("Missing kid in JWT header");
        }
        JwkProvider provider = new UrlJwkProvider(jwksUrl);
        Jwk jwk = provider.get(kid);
        return jwk.getPublicKey();
    }
}
