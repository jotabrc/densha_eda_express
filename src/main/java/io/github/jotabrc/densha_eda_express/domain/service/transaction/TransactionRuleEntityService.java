package io.github.jotabrc.densha_eda_express.domain.service.transaction;

import io.github.jotabrc.densha_eda_express.domain.entity.TransactionRuleEntity;
import io.github.jotabrc.densha_eda_express.domain.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransactionRuleEntityService extends BaseService<UUID, TransactionRuleEntity> {

    TransactionRuleEntity findByIdOrElseThrow(UUID id);

    TransactionRuleEntity findByTriggerTypeOrElseThrow(TransactionType type);

    Page<TransactionRuleEntity> findAll(Pageable pageable);
}
