package com.fengshui.common.security.impl;

import com.fengshui.common.aws.Cognito.CognitoUserPool;
import com.fengshui.common.security.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    @Autowired
    CognitoUserPool cognitoUserPool;

    @Override
    public Claims decodeJwt(String token) {
        Claims userClaims = null;
        try {
            PublicKey publicKey = cognitoUserPool.getPublicKeyFromJwk(token);
            userClaims = Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (SignatureException e) {
            System.err.println("Invalid JWT signature: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Failed to decode JWT token: " + e.getMessage());
        }
        return userClaims;
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Claims claims = decodeJwt(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
