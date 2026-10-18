package io.github.jotabrc.densha_eda_express.messaging;

import io.github.jotabrc.densha_eda_express.config.KafkaTopic;
import io.github.jotabrc.densha_eda_express.domain.Order;
import io.github.jotabrc.densha_eda_express.service.PaymentFulfillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentConsumer {

    private final PaymentFulfillService paymentFulfillService;

    @KafkaListener(topics = {KafkaTopic.TOPIC_INVENTORY_RESERVED, KafkaTopic.TOPIC_INVENTORY_RESERVED_RETRY})
    public void fulfill(@Payload final Order entity) {
        log.info("Fulfilling payment for order: {}", entity.getId());
        paymentFulfillService.fulfill(entity);
    }
}
