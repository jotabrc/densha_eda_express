package io.github.jotabrc.densha_eda_express.service.impl;

import io.github.jotabrc.densha_eda_express.config.KafkaTopic;
import io.github.jotabrc.densha_eda_express.domain.Order;
import io.github.jotabrc.densha_eda_express.domain.OutboxKey;
import io.github.jotabrc.densha_eda_express.domain.Payment;
import io.github.jotabrc.densha_eda_express.service.OutboxPublisherService;
import io.github.jotabrc.densha_eda_express.service.PaymentFulfillService;
import io.github.jotabrc.densha_eda_express.service.PaymentService;
import io.github.jotabrc.densha_eda_express.service.external.PaymentTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PaymentFulfillServiceImpl implements PaymentFulfillService {

    private final PaymentService paymentService;
    private final PaymentTransactionService paymentTransactionService;
    private final OutboxPublisherService outboxPublisherService;

    @Override
    public void fulfill(Order order) {
        try {
            Payment payment = paymentService.findByOrderId(order.getId());
            payment.setStatus(paymentTransactionService.fulfill(payment));

            outboxPublisherService.publish(() -> List.of(paymentService.save(payment)),
                    Map.of(OutboxKey.TOPIC, KafkaTopic.TOPIC_PAYMENT_FULFILLED), order);
        } catch (IllegalStateException e) {
            outboxPublisherService.publish(Map.of(OutboxKey.TOPIC, KafkaTopic.TOPIC_PAYMENT_FAILED), order);
        }
    }
}
