package io.github.jotabrc.densha_eda_express.service.impl;

import io.github.jotabrc.densha_eda_express.config.KafkaTopic;
import io.github.jotabrc.densha_eda_express.domain.Inventory;
import io.github.jotabrc.densha_eda_express.domain.Order;
import io.github.jotabrc.densha_eda_express.domain.OutboxKey;
import io.github.jotabrc.densha_eda_express.domain.Product;
import io.github.jotabrc.densha_eda_express.service.InventoryReserveService;
import io.github.jotabrc.densha_eda_express.service.InventoryService;
import io.github.jotabrc.densha_eda_express.service.OutboxPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class InventoryReserveServiceImpl implements InventoryReserveService {

    private final InventoryService inventoryService;
    private final OutboxPublisherService outboxPublisherService;

    @Override
    public void reserve(Order order) {
        Set<Inventory> inventories = new HashSet<>();
        for (Product product : order.getProducts()) {
            Inventory inventory = inventoryService.findByProductId(product.getId());

            if (inventories.contains(inventory)) {
                inventory = getInUseInventory(inventories, product);
            }

            if (inventory.reserve(product.getQuantity())) {
                inventories.add(inventory);
            } else {
                inventories = null;
                break;
            }
        }

        if (nonNull(inventories)) {
            final Set<Inventory> inventoryList = inventories;
            outboxPublisherService.publish(() -> inventoryService.saveAll(inventoryList),
                    Map.of(OutboxKey.TOPIC, KafkaTopic.TOPIC_INVENTORY_RESERVED), order);
        } else {
            outboxPublisherService.publish(Map.of(OutboxKey.TOPIC, KafkaTopic.TOPIC_INVENTORY_FAILED), order);
        }
    }

    @Override
    public void cancelReservation(Order order) {
        Set<Inventory> inventories = new HashSet<>();
        for (Product product : order.getProducts()) {
            Inventory inventory = inventoryService.findByProductId(product.getId());

            if (inventories.contains(inventory)) {
                inventory = getInUseInventory(inventories, product);
            }

            inventory.cancelReservation(product.getQuantity());
            inventories.add(inventory);
        }

        outboxPublisherService.publish(() -> inventoryService.saveAll(inventories),
                Map.of(OutboxKey.TOPIC, KafkaTopic.TOPIC_INVENTORY_DEDUCTED),
                order);
    }

    private Inventory getInUseInventory(Set<Inventory> inventories, Product product) {
        return inventories.stream()
                .filter(i -> Objects.equals(i.getProductId(), product.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(Inventory.class.getName() + " not found"));
    }
}
