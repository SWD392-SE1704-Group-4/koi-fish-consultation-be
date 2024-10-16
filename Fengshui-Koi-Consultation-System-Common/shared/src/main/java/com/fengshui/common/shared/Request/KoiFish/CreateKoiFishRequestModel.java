package com.fengshui.common.shared.Request.KoiFish;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class CreateKoiFishRequestModel implements Serializable {
    private String koiFishName;
    private String koiFishColor;
    private double koiFishSize;
    private double koiFishAge;
    private MultipartFile[] koiFishPictures;
    private UUID fengshuiElement;
    private String symbolicMeaning;
    private String energyType;
    private int favorableNumber;
    private String favorableColor;
    private String koiFishOrigin;
    private String koiFishDescription;
    private double koiFishPrice;
    private boolean deleted;
}
