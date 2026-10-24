package io.github.jotabrc.densha_eda_express.domain.service;

import io.github.jotabrc.densha_eda_express.domain.entity.TransactionRuleEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransactionRuleEntityServiceImpl implements TransactionRuleEntityService {

    private final TransactionRuleJpaService transactionRuleJpaService;

    @Override
    public TransactionRuleEntity findByIdOrElseThrow(UUID id) {
        return TransactionRuleEntity.fromJpa(transactionRuleJpaService.findByIdOrElseThrow(id));
    }

    @Override
    public TransactionRuleEntity findByTypeOrElseThrow(TransactionType type) {
        return TransactionRuleEntity.fromJpa(transactionRuleJpaService.findByTypeOrElseThrow(type));
    }

    @Override
    public Page<TransactionRuleEntity> findAll(Pageable pageable) {
        return transactionRuleJpaService.findAll(pageable).map(TransactionRuleEntity::fromJpa);
    }
}
