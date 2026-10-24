package io.github.jotabrc.densha_eda_express.domain.service;

public interface BaseService<UUID, T>{

    T findByIdOrElseThrow(UUID id);
}
