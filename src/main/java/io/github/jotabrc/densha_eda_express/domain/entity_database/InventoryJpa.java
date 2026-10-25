package io.github.jotabrc.densha_eda_express.domain.entity_database;


import io.github.jotabrc.densha_eda_express.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
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

    @Builder.Default
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<InventoryHistoryJpa> history = new ArrayList<>();
}
