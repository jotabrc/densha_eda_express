package io.github.jotabrc.densha_eda_express.service;

import io.github.jotabrc.densha_eda_express.domain.BaseEntity;
import io.github.jotabrc.densha_eda_express.domain.Outbox;
import io.github.jotabrc.densha_eda_express.domain.OutboxKey;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public interface OutboxPublisherService {

    <T extends BaseEntity, S extends BaseEntity> Outbox publish(Callable<List<S>> saveEntity, Map<OutboxKey, Object> properties, T event);

    Outbox publish(Outbox outbox);

    List<Outbox> publish(List<Outbox> outboxes);
}
