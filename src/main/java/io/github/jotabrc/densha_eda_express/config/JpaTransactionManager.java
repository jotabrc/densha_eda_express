package io.github.jotabrc.densha_eda_express.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaTransactionManager {

    @Bean("transactionManager")
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.transactionManager(entityManagerFactory);
        return jpaTransactionManager;
    }

}
