package com.snackbar.adapters.outbound.persistence.order;

import com.snackbar.adapters.outbound.persistence.order.repository.OrderJpaEntity;
import com.snackbar.adapters.outbound.persistence.order.repository.OrderRepository;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.ports.outbound.order.SaveOrderPort;
import org.springframework.stereotype.Component;

@Component
public class SaveOrderAdapter implements SaveOrderPort {

    private final OrderRepository repository;

    public SaveOrderAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        return this.repository.save(OrderJpaEntity.from(order)).toAggregate();
    }
}
