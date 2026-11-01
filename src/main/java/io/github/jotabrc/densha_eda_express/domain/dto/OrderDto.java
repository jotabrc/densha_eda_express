package io.github.jotabrc.densha_eda_express.domain.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
    private int version;
    private List<ProductDto> products;
}
