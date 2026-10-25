package io.github.jotabrc.densha_eda_express.domain.entity_database;


import io.github.jotabrc.densha_eda_express.domain.BaseObject;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class InventoryHistoryJpa extends BaseObject {

    @ManyToOne
    @Column(name = "inventory_id", nullable = false)
    private InventoryJpa inventory;

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    private Long reserved;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;
}
