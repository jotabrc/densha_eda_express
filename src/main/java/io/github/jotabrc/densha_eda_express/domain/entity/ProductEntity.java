package io.github.jotabrc.densha_eda_express.domain.entity;


import io.github.jotabrc.densha_eda_express.domain.BaseObject;
import io.github.jotabrc.densha_eda_express.domain.entity_database.ProductJpa;
import io.github.jotabrc.densha_eda_express.domain.status.ProcessingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductEntity extends BaseObject {

    private Long quantity;
    private BigDecimal price;
    private ProcessingStatus status;

    public static ProductEntity fromJpa(ProductJpa jpa) {
        return ProductEntity.builder()
                .quantity(jpa.getQuantity())
                .price(jpa.getPrice())
                .status(jpa.getStatus())
                .id(jpa.getId())
                .createdDate(jpa.getCreatedDate())
                .updatedDate(jpa.getUpdatedDate())
                .version(jpa.getVersion())
                .build();
    }
}
