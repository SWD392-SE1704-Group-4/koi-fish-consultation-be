package com.fengshui.common.shared.Request.AdsPackage;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class GetPackageByUserIdRequestModel {
    UUID appUserId;
}
