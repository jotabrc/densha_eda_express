package io.github.jotabrc.densha_eda_express.service.impl;

import io.github.jotabrc.densha_eda_express.domain.BaseEntity;
import io.github.jotabrc.densha_eda_express.domain.Outbox;
import io.github.jotabrc.densha_eda_express.domain.OutboxKey;
import io.github.jotabrc.densha_eda_express.domain.OutboxStatus;
import io.github.jotabrc.densha_eda_express.service.OutboxPublisherService;
import io.github.jotabrc.densha_eda_express.service.OutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxPublisherServiceImpl implements OutboxPublisherService {

    private final OutboxService outboxService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    @Override
    public <T extends BaseEntity, S extends BaseEntity> Outbox publish(Callable<List<S>> saveEntity, Map<OutboxKey, Object> properties, T event) {
        try {
            saveEntity.call();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        Outbox outbox = outboxService.create(event, properties);
        this.send(outbox);
        return outboxService.save(outbox);
    }

    @Transactional
    @Override
    public Outbox publish(Outbox outbox) {
        this.send(outbox);
        return outboxService.save(outbox);
    }

    @Transactional
    @Override
    public List<Outbox> publish(List<Outbox> outboxes) {
        for (Outbox outbox : outboxes) {
            this.send(outbox);
        }
        return outboxService.saveAll(outboxes);
    }

    private void send(Outbox outbox) {
        try {
            kafkaTemplate.send(outbox.getTopic(), outbox.getKey(), outbox.getEvent())
                    .thenAccept(result -> {
                        log.info("Outbox published {}: {}", outbox.getId(), result.getRecordMetadata());
                        outbox.setStatus(OutboxStatus.SENT);
                    }).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }
}
