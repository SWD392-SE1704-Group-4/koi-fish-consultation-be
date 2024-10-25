package com.fengshui.common.shared.Request.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateTransactionRequestModel implements Serializable {
    private UUID transaction_id;
    private LocalDateTime createdAt;
    private String userId;
    private String adsPackage;
    private double price;
    //   private boolean deleted;
}
