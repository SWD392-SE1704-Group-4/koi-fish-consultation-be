package com.fengshui.common.repository.postgresql.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
public class Transaction {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name = "transaction_id")
    private UUID transaction_id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn (name = "user_id", referencedColumnName = "user_id")
    private String userId;

    @Column (name = "ads_package")
    private String adsPackage;

    @Column (name = "price")
    private double price;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}
