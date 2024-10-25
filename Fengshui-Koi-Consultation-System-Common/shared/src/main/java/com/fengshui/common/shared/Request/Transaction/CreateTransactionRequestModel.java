package com.fengshui.common.shared.Request.Transaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateTransactionRequestModel {
    private UUID transaction_id;
    private LocalDateTime createdAt;
    private String userId;
    private String adsPackage;
    private double price;
    //   private boolean deleted;
}
