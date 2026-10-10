package io.github.jotabrc.densha_eda_express.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import tools.jackson.databind.ser.jackson.JsonValueSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducer {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimeout;

    @Value("${spring.kafka.producer.properties.linger.ms}")
    private String linger;

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeout;

    @Value("${spring.kafka.producer.transactional-id-prefix}")
    private String transactionalIdPrefix;

    @Value("${spring.kafka.producer.properties.max.in.flight.requests.per.connection}")
    private String inflightRequests;

    @Bean
    ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonValueSerializer.class);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.ACKS_CONFIG, acks);
        properties.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeout);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout);
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, inflightRequests);
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionalIdPrefix);
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean("kafkaTransactionManager")
    KafkaTransactionManager<String, Object> kafkaTransactionManager(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTransactionManager<>(producerFactory);
    }

    @Bean
    KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
