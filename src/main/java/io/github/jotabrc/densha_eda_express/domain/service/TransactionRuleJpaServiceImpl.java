package io.github.jotabrc.densha_eda_express.domain.service;

import io.github.jotabrc.densha_eda_express.domain.entity_database.TransactionRuleJpa;
import io.github.jotabrc.densha_eda_express.domain.exception.RegisterNotFoundException;
import io.github.jotabrc.densha_eda_express.domain.service.repositories.TransactionRuleRepository;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransactionRuleJpaServiceImpl implements TransactionRuleJpaService {

    private final TransactionRuleRepository repo;

    @Override
    public TransactionRuleJpa findByIdOrElseThrow(UUID id) {
        return repo.findById(id).orElseThrow(() -> new RegisterNotFoundException(TransactionRuleJpa.class.getSimpleName(), id.toString()));
    }

    @Override
    public TransactionRuleJpa findByTypeOrElseThrow(TransactionType type) {
        return repo.findByType(type).orElseThrow(() -> new RegisterNotFoundException(TransactionRuleJpa.class.getSimpleName(), type.toString()));
    }

    @Override
    public Page<TransactionRuleJpa> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
