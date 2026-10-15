package io.github.jotabrc.densha_eda_express.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_product")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @DecimalMin("0")
    @Column(nullable = false)
    private BigDecimal quantity;

    @DecimalMin("0.00")
    @Column(nullable = false)
    private BigDecimal price;
}
