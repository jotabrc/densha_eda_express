package io.github.jotabrc.densha_eda_express.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.util.backoff.ExponentialBackOff;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaConsumer {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.properties.spring.json.trusted.packages}")
    private String trustedPackages;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${densha.kafka.consumer.backoff.initial-interval:2000}")
    private Long initialInterval;

    @Value("${densha.kafka.consumer.backoff.multiplier:2}")
    private Long multiplier;

    @Value("${densha.kafka.consumer.backoff.max-interval:10000}")
    private Long maxInterval;

    @Value("${densha.kafka.consumer.backoff.max-attempts:3}")
    private Long maxAttempts;

    private final ExceptionsClassification exceptionsClassifications;

    @Bean
    ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        properties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JacksonJsonDeserializer.class);
        properties.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, trustedPackages);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(ConsumerFactory<String, Object> consumerFactory,
                                                                                          KafkaTemplate<String, Object> kafkaTemplate) {
        ExponentialBackOff backOff = new ExponentialBackOff(initialInterval, multiplier);
        backOff.setMaxInterval(maxInterval);
        backOff.setMaxAttempts(maxAttempts);

        DefaultErrorHandler errorHandler = getErrorHandler(kafkaTemplate, backOff);
        errorHandler.setClassifications(exceptionsClassifications.getClassifications(), true);

        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }

    private DefaultErrorHandler getErrorHandler(KafkaTemplate<String, Object> kafkaTemplate, ExponentialBackOff backOff) {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
                (consumerRecord, exception) -> {
                    int attempt = getRetryAttempt(consumerRecord);

                    if (attempt > maxAttempts) {
                        return new TopicPartition("app.cmd.send-to-dlq.0", consumerRecord.partition());
                    } else {
                        return new TopicPartition(consumerRecord.topic(), consumerRecord.partition());
                    }
                }
        );

        return new DefaultErrorHandler(recoverer, backOff);
    }

    private int getRetryAttempt(ConsumerRecord<?, ?> record) {
        var header = record.headers().lastHeader("kafka_retry_count");
        if (header == null) {
            return 1;
        }
        return Integer.parseInt(new String(header.value())) + 1;
    }
}
