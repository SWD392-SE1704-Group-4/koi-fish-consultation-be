package com.fengshui.common.shared.Request.Advertisement;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GetAdvertisementByElementOrDirectionRequestModel {
    UUID elementId;
    UUID directionId;
}
