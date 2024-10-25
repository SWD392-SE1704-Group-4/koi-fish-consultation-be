package com.fengshui.common.shared.Request.FishPond;

import com.fengshui.common.shared.Response.BaseResponseModel;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class CreateFishPondRequestModel implements Serializable {
    private String pondName;
    private String pondShape;
    private Double pondSize;
    private Double pondDepth;
    private String pondMaterial;
    private Boolean hasWaterfall;
    private Boolean hasPlants;
    private Boolean hasRocks;
    private Boolean isSaltwater;
    private Integer numKoiFish;
    private Double waterCapacity;  // in liters or cubic meters
    private String pondLocation;  // Indoor or outdoor
    private String pondOrientation;  // Direction the pond is facing
    private MultipartFile[] fishPondPictures;
    private UUID fengshuiElement;
}
