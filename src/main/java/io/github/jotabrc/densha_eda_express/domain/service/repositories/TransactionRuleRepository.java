package io.github.jotabrc.densha_eda_express.domain.service.repositories;

import io.github.jotabrc.densha_eda_express.domain.entity_database.TransactionRuleJpa;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRuleRepository extends CrudRepository<UUID, TransactionRuleJpa> {

    Optional<TransactionRuleJpa> findById(UUID id);

    Optional<TransactionRuleJpa> findByTriggerType(TransactionType type);

    Page<TransactionRuleJpa> findAll(Pageable pageable);
}
