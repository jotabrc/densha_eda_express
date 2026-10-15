package io.github.jotabrc.densha_eda_express.service;

import io.github.jotabrc.densha_eda_express.domain.Inventory;

import java.util.List;
import java.util.UUID;

public interface InventoryService extends BaseService<Inventory> {

    Inventory findByProductId(UUID productId);

    List<Inventory> saveAll(Iterable<Inventory> inventories);
}
