package com.fengshui.common.repository.postgresql.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Advertiser extends AppUser{
    @Column(name = "total_ads_posted")
    private int totalAdsPosted;

    @Column(name = "subscription_type")
    private String subscriptionType;  // e.g., Free, Premium, etc.

    @Column(name = "account_balance")
    private BigDecimal accountBalance;
}
