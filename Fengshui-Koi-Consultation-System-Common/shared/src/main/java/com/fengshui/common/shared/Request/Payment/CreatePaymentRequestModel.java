package com.fengshui.common.shared.Request.Payment;

import lombok.Getter;
import lombok.Setter;
import vn.payos.type.ItemData;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreatePaymentRequestModel {
    private UUID appUserId;
    private BigDecimal totalAmount;
    private String note;
    private ItemData item;
    private UUID adsPackageId;
}
