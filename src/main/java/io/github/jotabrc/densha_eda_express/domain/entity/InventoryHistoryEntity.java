package io.github.jotabrc.densha_eda_express.domain.entity;


import io.github.jotabrc.densha_eda_express.domain.BaseObject;
import io.github.jotabrc.densha_eda_express.domain.entity_database.InventoryHistoryJpa;
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
    private InventoryEntity inventory;
    private UUID orderId;
    private TransactionType type;

    public static InventoryHistoryEntity fromJpa(InventoryHistoryJpa jpa) {
        return InventoryHistoryEntity.builder()
                .stock(jpa.getStock())
                .reserved(jpa.getReserved())
                .inventory(InventoryEntity.fromJpa(jpa.getInventory()))
                .orderId(jpa.getOrder().getId())
                .type(jpa.getType())
                .id(jpa.getId())
                .createdDate(jpa.getCreatedDate())
                .updatedDate(jpa.getUpdatedDate())
                .version(jpa.getVersion())
                .build();
    }
}
