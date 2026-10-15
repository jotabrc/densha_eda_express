package io.github.jotabrc.densha_eda_express.service.impl;

import io.github.jotabrc.densha_eda_express.domain.Payment;
import io.github.jotabrc.densha_eda_express.repository.PaymentRepository;
import io.github.jotabrc.densha_eda_express.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    @Override
    public Payment save(final Payment entity) {
        return repository.save(entity);
    }

    @Override
    public Payment update(final Payment entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(final Payment entity) {
        repository.delete(entity);
    }

    @Override
    public Payment findById(final UUID id) {
        return repository.findById(UUID.fromString(id.toString()))
                .orElseThrow(() -> new IllegalStateException(Payment.class.getSimpleName() + " not found: %s".formatted(id)));
    }
}
