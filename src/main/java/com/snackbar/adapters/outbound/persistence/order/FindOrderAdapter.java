package com.snackbar.adapters.outbound.persistence.order;

import com.snackbar.adapters.outbound.persistence.order.repository.OrderJpaEntity;
import com.snackbar.adapters.outbound.persistence.order.repository.OrderRepository;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.Status;
import com.snackbar.application.ports.outbound.order.FindAllOrdersPort;
import com.snackbar.application.ports.outbound.order.FindOrdersByStatusPort;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindOrderAdapter implements FindOrdersByStatusPort, FindAllOrdersPort {

    private final OrderRepository repository;

    public FindOrderAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> findOrdersByStatus(Status status) {
        OrderJpaEntity orderJpaEntity = OrderJpaEntity.from(status);
        Example<OrderJpaEntity> example = Example.of(orderJpaEntity);
        return this.repository.findAll(example).stream().map(OrderJpaEntity::toAggregate).toList();
    }

    @Override
    public List<Order> findAllOrders() {
        return this.repository.findAll().stream().map(OrderJpaEntity::toAggregate).toList();
    }
}
