package com.snackbar.adapters.outbound.persistence.order;

import com.snackbar.adapters.outbound.persistence.order.repository.OrderJpaEntity;
import com.snackbar.adapters.outbound.persistence.order.repository.OrderRepository;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.ports.outbound.order.FindAllOrdersPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindOrderAdapter implements FindAllOrdersPort {

    private final OrderRepository repository;

    public FindOrderAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> findAllOrders() {
        return this.repository.findAll().stream().map(OrderJpaEntity::toAggregate).toList();
    }
}
