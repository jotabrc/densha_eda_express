package io.github.jotabrc.densha_eda_express.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class InventoryDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
    private int version;
    private Long stock;
    private Long reserved;
    private UUID productId;
    private List<InventoryHistoryDto> history;
}
