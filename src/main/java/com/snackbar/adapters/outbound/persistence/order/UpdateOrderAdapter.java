package com.snackbar.adapters.outbound.persistence.order;

import com.snackbar.adapters.outbound.persistence.order.repository.OrderJpaEntity;
import com.snackbar.adapters.outbound.persistence.order.repository.OrderRepository;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.ports.outbound.order.UpdateOrderPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrderAdapter implements UpdateOrderPort {

    private final OrderRepository repository;

    public UpdateOrderAdapter(OrderRepository repository) {
        this.repository = repository;
    }
    @Override
    public Order update(Order order) {
        return this.repository.save(OrderJpaEntity.from(order)).toAggregate();
    }
}
