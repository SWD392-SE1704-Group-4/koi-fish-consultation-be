package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.FengshuiDirectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFengshuiDirectionRepository extends JpaRepository<FengshuiDirectionEntity, UUID> {
}
