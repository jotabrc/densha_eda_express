package io.github.jotabrc.densha_eda_express.service.impl;

import io.github.jotabrc.densha_eda_express.domain.Order;
import io.github.jotabrc.densha_eda_express.domain.OrderStatus;
import io.github.jotabrc.densha_eda_express.service.OrderCancelService;
import io.github.jotabrc.densha_eda_express.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderCancelServiceImpl implements OrderCancelService {

    private final OrderService orderService;

    @Override
    public Order cancel(Order order) {
        Order savedOrder = orderService.findById(order.getId());
        savedOrder.setStatus(OrderStatus.CANCELLED);
        return orderService.save(savedOrder);
    }
}
