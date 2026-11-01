package io.github.jotabrc.densha_eda_express.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopic {

    public static final String TOPIC_INVENTORY_TRANSACTION = "order.cmd.inventory-transaction.0";

    @Bean
    NewTopic inventoryTransaction() {
        return new NewTopic(TOPIC_INVENTORY_TRANSACTION, 3, (short) 2);
    }
}
