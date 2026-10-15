package io.github.jotabrc.densha_eda_express.messaging;

import io.github.jotabrc.densha_eda_express.config.KafkaTopic;
import io.github.jotabrc.densha_eda_express.domain.Order;
import io.github.jotabrc.densha_eda_express.service.OrderCancelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderConsumer {

    private final OrderCancelService orderCancelService;

    @KafkaListener(topics = {KafkaTopic.TOPIC_INVENTORY_FAILED, KafkaTopic.TOPIC_INVENTORY_FAILED_RETRY})
    public void consume(@Payload final Order entity) {
        log.info("Cancelling order: {}", entity.getId());
        orderCancelService.cancel(entity);
    }
}
