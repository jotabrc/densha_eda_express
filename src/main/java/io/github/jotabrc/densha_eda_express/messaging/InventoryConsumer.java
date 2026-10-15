package io.github.jotabrc.densha_eda_express.messaging;

import io.github.jotabrc.densha_eda_express.config.KafkaTopic;
import io.github.jotabrc.densha_eda_express.domain.Order;
import io.github.jotabrc.densha_eda_express.service.InventoryReserveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class InventoryConsumer {

    private final InventoryReserveService inventoryReserveService;

    @KafkaListener(topics = {KafkaTopic.TOPIC_ORDER_CREATED, KafkaTopic.TOPIC_ORDER_CREATED_RETRY})
    public void reserveInventory(@Payload final Order entity) {
        log.info("Reserving inventory for order: {}", entity.getId());
        inventoryReserveService.reserve(entity);
    }

    @KafkaListener(topics = {KafkaTopic.TOPIC_PAYMENT_FAILED, KafkaTopic.TOPIC_PAYMENT_FAILED_RETRY})
    public void compensateInventory(@Payload final Order entity) {
        log.info("Cancelling inventory reservation for order: {}", entity.getId());
        inventoryReserveService.cancelReservation(entity);
    }
}
