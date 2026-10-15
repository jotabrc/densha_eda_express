package io.github.jotabrc.densha_eda_express.service.impl;

import io.github.jotabrc.densha_eda_express.domain.Shipping;
import io.github.jotabrc.densha_eda_express.repository.ShippingRepository;
import io.github.jotabrc.densha_eda_express.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository repository;

    @Override
    public Shipping save(final Shipping entity) {
        return repository.save(entity);
    }

    @Override
    public Shipping update(final Shipping entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(final Shipping entity) {
        repository.delete(entity);
    }

    @Override
    public Shipping findById(final UUID id) {
        return repository.findById(UUID.fromString(id.toString()))
                .orElseThrow(() -> new IllegalStateException(Shipping.class.getSimpleName() + " not found: %s".formatted(id)));
    }
}
