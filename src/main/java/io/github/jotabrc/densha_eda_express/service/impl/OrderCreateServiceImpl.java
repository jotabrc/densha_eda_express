package io.github.jotabrc.densha_eda_express.service.impl;

import io.github.jotabrc.densha_eda_express.config.KafkaTopic;
import io.github.jotabrc.densha_eda_express.domain.Order;
import io.github.jotabrc.densha_eda_express.domain.Outbox;
import io.github.jotabrc.densha_eda_express.domain.OutboxKey;
import io.github.jotabrc.densha_eda_express.service.OrderCreateService;
import io.github.jotabrc.densha_eda_express.service.OrderService;
import io.github.jotabrc.densha_eda_express.service.OutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Callable;

@RequiredArgsConstructor
@Service
public class OrderCreateServiceImpl implements OrderCreateService {

    private final OrderService orderService;
    private final OutboxService outboxService;

    @Override
    public Order create(Order order) {
        Callable<Order> saveOrder = () -> orderService.save(order);
        Outbox outbox = outboxService.saveAll(saveOrder, Map.of(
                OutboxKey.TOPIC, KafkaTopic.TOPIC_ORDER_CREATED
        ));
        return (Order) outbox.getEntity();
    }
}
