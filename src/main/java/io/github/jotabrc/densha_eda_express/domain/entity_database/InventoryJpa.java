package io.github.jotabrc.densha_eda_express.domain.entity_database;


import io.github.jotabrc.densha_eda_express.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "tb_inventory")
public class InventoryJpa extends BaseEntity {

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    private Long reserved;

    @Column(name = "product_id", nullable = false, unique = true)
    private UUID productId;
}
