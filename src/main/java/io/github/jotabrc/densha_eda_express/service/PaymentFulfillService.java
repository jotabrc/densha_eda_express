package io.github.jotabrc.densha_eda_express.service;

import io.github.jotabrc.densha_eda_express.domain.Order;

public interface PaymentFulfillService {

    void fulfill(Order order);
}
