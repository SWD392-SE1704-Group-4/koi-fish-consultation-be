package com.fengshui.common.shared.Response.AppUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserLoginRequestModel {
    String email;
    String password;
}
