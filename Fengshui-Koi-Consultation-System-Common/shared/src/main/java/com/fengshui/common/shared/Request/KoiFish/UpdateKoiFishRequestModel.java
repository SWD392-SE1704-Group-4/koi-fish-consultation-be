package com.fengshui.common.shared.Request.KoiFish;

import com.fengshui.common.repository.postgresql.dto.KoiFishDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class UpdateKoiFishRequestModel implements Serializable {
    private UUID koiFishId;
    private String koiFishName;
    private String koiFishColor;
    private double koiFishSize;
    private double koiFishAge;
    private ArrayList<String> koiFishPictures;
    private MultipartFile[] newKoiFishPictures;
    private UUID fengshuiElement;
    private String symbolicMeaning;
    private String energyType;
    private int favorableNumber;
    private String favorableColor;
    private String koiFishOrigin;
    private String koiFishDescription;
    private double koiFishPrice;
}
