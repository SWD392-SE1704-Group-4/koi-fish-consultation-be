package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    Optional<TransactionEntity> findById(UUID uuid);
}
