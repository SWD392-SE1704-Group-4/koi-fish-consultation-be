package com.fengshui.common.shared.Request.KoiFish;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateKoiFishRequestModel implements Serializable {
    private String koiFishName;
    private String koiFishColor;
    private double koiFishSize;
    private double koiFishAge;
    private String koiFishPicture;
}
