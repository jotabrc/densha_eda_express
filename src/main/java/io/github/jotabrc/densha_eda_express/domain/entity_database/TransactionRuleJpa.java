package io.github.jotabrc.densha_eda_express.domain.entity_database;

import io.github.jotabrc.densha_eda_express.domain.BaseEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import jakarta.persistence.*;
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
@Entity
@Table(name = "tb_transaction_rule")
public class TransactionRuleJpa extends BaseEntity {

    @Column(name = "bean_name", nullable = false)
    private String beanName;

    @Enumerated(EnumType.STRING)
    @Column(name = "trigger_type", nullable = false)
    private TransactionType triggerType;

    @Column(name = "types")
    private List<TransactionType> types;
}
