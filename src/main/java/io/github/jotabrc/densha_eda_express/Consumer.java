package io.github.jotabrc.densha_eda_express;

import io.github.jotabrc.densha_eda_express.config.KafkaTopic;
import io.github.jotabrc.densha_eda_express.domain.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
public class Consumer {

    @KafkaListener(topics = KafkaTopic.TOPIC_INVENTORY_TRANSACTION)
    public void inventoryTransaction(OrderDto order) {
    }
}
