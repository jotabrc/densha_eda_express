package io.github.jotabrc.densha_eda_express.domain.entity;


import io.github.jotabrc.densha_eda_express.domain.BaseObject;
import io.github.jotabrc.densha_eda_express.domain.entity_database.OrderJpa;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderEntity extends BaseObject {

    @Builder.Default
    @ManyToMany
    private List<ProductEntity> products = new ArrayList<>();

    public BigDecimal getTotalPrice() {
        return products.stream()
                .map(ProductEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static OrderEntity fromJpa(OrderJpa jpa) {
        return OrderEntity.builder()
                .products(jpa.getProducts().stream()
                        .map(ProductEntity::fromJpa)
                        .toList())
                .id(jpa.getId())
                .createdDate(jpa.getCreatedDate())
                .updatedDate(jpa.getUpdatedDate())
                .version(jpa.getVersion())
                .build();
    }
}
