package io.github.jotabrc.densha_eda_express.domain.dto;

import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRuleDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
    private int version;
    private String beanName;
    private TransactionType triggerType;
    private List<TransactionType> types;
}
