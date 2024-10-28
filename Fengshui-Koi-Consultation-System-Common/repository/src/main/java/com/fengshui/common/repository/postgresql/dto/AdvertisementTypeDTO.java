package com.fengshui.common.repository.postgresql.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AdvertisementTypeDTO {
    private UUID id;
    private String typeName;
    private String description;
}
