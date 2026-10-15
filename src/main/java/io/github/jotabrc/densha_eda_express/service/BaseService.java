package io.github.jotabrc.densha_eda_express.service;

import java.util.UUID;

public interface BaseService<T> {

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(UUID id);
}
