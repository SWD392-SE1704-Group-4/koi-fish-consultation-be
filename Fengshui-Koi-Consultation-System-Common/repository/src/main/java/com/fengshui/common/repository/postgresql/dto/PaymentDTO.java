package com.fengshui.common.repository.postgresql.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private UUID id;
    private AppUserDTO user;
    private String note;
    private Long orderCode;
    private BigDecimal amount;
    private String currency;
    private String checkoutUrl;
    private String paymentLinkId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}