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
    private Double waterCapacity;
    private String pondLocation;
    private UUID pondOrientation;
    private MultipartFile[] fishPondPictures;
    private UUID createdBy;
}
