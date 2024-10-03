package com.fengshui.common.repository.postgresql;

import com.fengshui.common.repository.postgresql.entities.FengshuiElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IFengshuiElementRepository extends JpaRepository<FengshuiElementEntity, UUID> {
}
