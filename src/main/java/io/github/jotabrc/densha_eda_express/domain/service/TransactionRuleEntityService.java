package io.github.jotabrc.densha_eda_express.domain.service;

import io.github.jotabrc.densha_eda_express.domain.entity.TransactionRuleEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransactionRuleEntityService extends BaseService<UUID, TransactionRuleEntity> {

    TransactionRuleEntity findByIdOrElseThrow(UUID id);

    TransactionRuleEntity findByTypeOrElseThrow(TransactionType type);

    Page<TransactionRuleEntity> findAll(Pageable pageable);
}
