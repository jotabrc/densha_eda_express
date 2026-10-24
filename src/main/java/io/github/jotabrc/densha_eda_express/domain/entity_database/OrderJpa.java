package io.github.jotabrc.densha_eda_express.domain.entity_database;


import io.github.jotabrc.densha_eda_express.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tb_order")
public class OrderJpa extends BaseEntity {

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "tb_order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductJpa> products = new ArrayList<>();
}
