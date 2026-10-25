package io.github.jotabrc.densha_eda_express.domain.entity;


import io.github.jotabrc.densha_eda_express.domain.BaseObject;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class InventoryHistoryEntity extends BaseObject {

    private Long stock;
    private Long reserved;
    private UUID productId;
    private TransactionType type;
}
