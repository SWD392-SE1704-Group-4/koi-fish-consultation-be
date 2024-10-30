package com.fengshui.common.repository.postgresql.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserLoginDTO {
    private String accessToken;
    private String idToken;
    private String refreshToken;

    public AppUserLoginDTO(String accessToken, String idToken, String refreshToken) {
    }
}
