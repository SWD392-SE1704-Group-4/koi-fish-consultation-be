package com.fengshui.common.shared.Request.AppUser;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GetAppUserByIdRequestModel {
    UUID appUserId;
}
