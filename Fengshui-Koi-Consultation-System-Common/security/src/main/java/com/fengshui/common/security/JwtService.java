package com.fengshui.common.security;

import io.jsonwebtoken.Claims;

public interface JwtService {
    Claims decodeJwt(String token);

    boolean isTokenValid(String token);
}
