package com.fengshui.common.shared.Request.Advertisement;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateAdvertisementRequestModel {
    private String title;
    private String description;
    private UUID advertisementType;
    private LocalDateTime expirationDate;
    private UUID koiFishId;
    private String postedBy;
    private MultipartFile[] additionalImages;
    private List<String> tags;
    private String location;
    private String contactInfo;
    private String phone;
    private String address;
}
