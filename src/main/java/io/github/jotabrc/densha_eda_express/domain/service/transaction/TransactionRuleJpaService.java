package io.github.jotabrc.densha_eda_express.domain.service.transaction;

import io.github.jotabrc.densha_eda_express.domain.entity_database.TransactionRuleJpa;
import io.github.jotabrc.densha_eda_express.domain.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransactionRuleJpaService extends BaseService<UUID, TransactionRuleJpa> {

    TransactionRuleJpa findByIdOrElseThrow(UUID id);

    TransactionRuleJpa findByTriggerTypeOrElseThrow(TransactionType type);

    Page<TransactionRuleJpa> findAll(Pageable pageable);
}
