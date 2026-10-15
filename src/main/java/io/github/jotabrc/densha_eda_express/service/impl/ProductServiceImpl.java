package io.github.jotabrc.densha_eda_express.service.impl;

import io.github.jotabrc.densha_eda_express.domain.Product;
import io.github.jotabrc.densha_eda_express.repository.ProductRepository;
import io.github.jotabrc.densha_eda_express.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Product save(final Product entity) {
        return repository.save(entity);
    }

    @Override
    public Product update(final Product entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(final Product entity) {
        repository.delete(entity);
    }

    @Override
    public Product findById(final UUID id) {
        return repository.findById(UUID.fromString(id.toString()))
                .orElseThrow(() -> new IllegalStateException(Product.class.getSimpleName() + " not found: %s".formatted(id)));
    }
}
