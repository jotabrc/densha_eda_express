package io.github.jotabrc.densha_eda_express.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jotabrc.densha_eda_express.domain.BaseEntity;
import io.github.jotabrc.densha_eda_express.domain.Outbox;
import io.github.jotabrc.densha_eda_express.domain.OutboxKey;
import io.github.jotabrc.densha_eda_express.domain.OutboxStatus;
import io.github.jotabrc.densha_eda_express.repository.OutboxRepository;
import io.github.jotabrc.densha_eda_express.service.OutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Service
@RequiredArgsConstructor
public class OutboxServiceImpl implements OutboxService {

    private final ObjectMapper objectMapper;
    private final OutboxRepository repository;

    @Override
    public Outbox save(Outbox outbox) {
        return repository.save(outbox);
    }

    @Transactional
    @Override
    public List<Outbox> saveAll(List<Outbox> outboxes) {
        return repository.saveAll(outboxes);
    }

    @Transactional
    @Override
    public <T extends BaseEntity> Outbox save(T event, Map<OutboxKey, Object> properties) {
        return repository.save(create(event, properties));
    }

    @Transactional
    @Override
    public <T extends BaseEntity> List<Outbox> saveAll(final Callable<List<T>> saveEntity, final Map<OutboxKey, Object> properties) {
        try {
            List<T> entities = saveEntity.call();
            List<Outbox> outboxes = new ArrayList<>();
            for (T entity : entities) {
                outboxes.add(create(entity, properties));
            }
            return repository.saveAll(outboxes);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public <T extends BaseEntity> Outbox create(T entity, Map<OutboxKey, Object> properties) {
        try {
            return Outbox.builder()
                    .retries(0)
                    .topic((String) properties.get(OutboxKey.TOPIC))
                    .key((String) properties.getOrDefault(OutboxKey.PRODUCER_KEY, entity.getId()))
                    .status(OutboxStatus.PENDING)
                    .entity(entity)
                    .event(objectMapper.writeValueAsBytes(entity))
                    .eventFqcn(entity.getClass().getName())
                    .build();
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Page<Outbox> findByStatus(final OutboxStatus status, Pageable pageable) {
        return repository.findAllByStatus(status, pageable);
    }

    @Override
    public void deleteAll(List<Outbox> outboxes) {
        repository.deleteAll(outboxes);
    }
}
