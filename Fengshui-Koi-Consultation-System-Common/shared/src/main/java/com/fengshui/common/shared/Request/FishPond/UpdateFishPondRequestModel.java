package com.fengshui.common.shared.Request.FishPond;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class UpdateFishPondRequestModel implements Serializable {
    private UUID fishPondId;
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
    private String pondElement;
    private String pondLocation;
    private UUID pondOrientation;
    private MultipartFile[] fishPondPictures;
}
