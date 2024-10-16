package com.fengshui.common.shared.Request.KoiFish;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
public class BaseKoiFishRequestModel {
    private String koiFishId;
    private String koiFishName;
    private String koiFishColor;
    private double koiFishSize;
    private double koiFishAge;
    private MultipartFile[] koiFishPicture;
    private UUID fengShuiElementId;
    private String symbolicMeaning;
    private String energyType;
    private int favorableNumber;
    private String favorableColor;
    private String koiFishOrigin;
    private String koiFishDescription;
    private double koiFishPrice;
    private double deleted;
}
