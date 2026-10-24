package io.github.jotabrc.densha_eda_express.domain.entity;

import io.github.jotabrc.densha_eda_express.domain.BaseObject;
import io.github.jotabrc.densha_eda_express.domain.entity_database.TransactionRuleJpa;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TransactionRuleEntity extends BaseObject {

    private String beanName;
    private TransactionType triggerType;
    private List<TransactionType> types;

    public static TransactionRuleEntity fromJpa(TransactionRuleJpa jpa) {
        return TransactionRuleEntity.builder()
                .id(jpa.getId())
                .beanName(jpa.getBeanName())
                .triggerType(jpa.getTriggerType())
                .types(jpa.getTypes())
                .createdDate(jpa.getCreatedDate())
                .updatedDate(jpa.getUpdatedDate())
                .version(jpa.getVersion())
                .build();
    }
}
