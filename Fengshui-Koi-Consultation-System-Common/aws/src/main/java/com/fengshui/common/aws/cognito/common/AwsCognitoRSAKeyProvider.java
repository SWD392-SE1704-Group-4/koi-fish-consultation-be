package com.fengshui.common.aws.cognito.common;

import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.auth0.jwk.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class AwsCognitoRSAKeyProvider implements RSAKeyProvider {
    private final URL jwkUrl;

    public AwsCognitoRSAKeyProvider(String url) {
        try {
            this.jwkUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(String.format("Invalid URL provided, URL=%s", url));
        }
    }

    @Override
    public RSAPublicKey getPublicKeyById(String keyId) {
        try {
            JwkProvider provider = new JwkProviderBuilder(jwkUrl).build();
            Jwk jwk = provider.get(keyId);
            return (RSAPublicKey) jwk.getPublicKey();
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Failed to get JWT kid=%s from aws_kid_store_url=%s", keyId, jwkUrl));
        }
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return null;
    }

    @Override
    public String getPrivateKeyId() {
        return null;
    }
}
