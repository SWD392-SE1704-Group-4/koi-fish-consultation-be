package com.fengshui.common.shared.Request.AppUser;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetAppUserGroupRequestModel implements Serializable {
    String userName;
}
