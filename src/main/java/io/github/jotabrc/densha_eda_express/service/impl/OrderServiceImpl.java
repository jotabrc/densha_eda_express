package io.github.jotabrc.densha_eda_express.service.impl;

import io.github.jotabrc.densha_eda_express.domain.Order;
import io.github.jotabrc.densha_eda_express.repository.OrderRepository;
import io.github.jotabrc.densha_eda_express.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public Order save(final Order entity) {
        return repository.save(entity);
    }

    @Override
    public Order update(final Order entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(final Order entity) {
        repository.delete(entity);
    }

    @Override
    public Order findById(final UUID id) {
        return repository.findById(UUID.fromString(id.toString()))
                .orElseThrow(() -> new IllegalStateException(Order.class.getSimpleName() + " not found: %s".formatted(id)));
    }
}
