package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPaymentRepository extends JpaRepository<PaymentEntity, UUID> {

}
