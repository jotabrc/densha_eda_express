package io.github.jotabrc.densha_eda_express.service;

import io.github.jotabrc.densha_eda_express.domain.Payment;

import java.util.UUID;

public interface PaymentService extends BaseService<Payment> {

    Payment findByOrderId(UUID orderId);
}
