package io.github.jotabrc.densha_eda_express.domain.dto;

import io.github.jotabrc.densha_eda_express.domain.status.ProcessingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
    private int version;
    private Long quantity;
    private BigDecimal price;
    private ProcessingStatus status;
}
