package io.github.jotabrc.densha_eda_express.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "densha.kafka.error-handler")
public class ExceptionsClassification {

    private Map<Class<? extends Throwable>, Boolean> classifications = new HashMap<>();
}
