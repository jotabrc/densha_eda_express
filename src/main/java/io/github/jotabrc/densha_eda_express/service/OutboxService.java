package io.github.jotabrc.densha_eda_express.service;

import io.github.jotabrc.densha_eda_express.domain.BaseEntity;
import io.github.jotabrc.densha_eda_express.domain.Outbox;
import io.github.jotabrc.densha_eda_express.domain.OutboxKey;
import io.github.jotabrc.densha_eda_express.domain.OutboxStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public interface OutboxService {

    Outbox save(Outbox outbox);

    List<Outbox> saveAll(List<Outbox> outboxes);

    <T extends BaseEntity> Outbox save(T event, Map<OutboxKey, Object> properties);

    <T extends BaseEntity> List<Outbox> saveAll(Callable<List<T>> saveEntity, Map<OutboxKey, Object> properties);

    <T extends BaseEntity> Outbox create(T entity, Map<OutboxKey, Object> properties);

    Page<Outbox> findByStatus(OutboxStatus status, Pageable pageable);

    void deleteAll(List<Outbox> outboxes);
}
