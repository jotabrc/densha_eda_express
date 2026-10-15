package io.github.jotabrc.densha_eda_express.service;

import io.github.jotabrc.densha_eda_express.domain.Order;

public interface InventoryReserveService {

    void reserve(Order order);

    void cancelReservation(Order order);
}
