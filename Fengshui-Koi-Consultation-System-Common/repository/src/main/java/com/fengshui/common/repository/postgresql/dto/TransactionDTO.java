package com.fengshui.common.repository.postgresql.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TransactionDTO {
    private UUID id;
    private LocalDateTime createdAt;
    private UUID appUser;
    private String adsPackage;
    private double price;
}
