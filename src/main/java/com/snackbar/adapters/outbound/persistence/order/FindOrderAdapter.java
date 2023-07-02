package com.snackbar.adapters.outbound.persistence.order;

import com.snackbar.adapters.outbound.persistence.order.repository.OrderJpaEntity;
import com.snackbar.adapters.outbound.persistence.order.repository.OrderRepository;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.core.domain.order.OrderStatus;
import com.snackbar.application.ports.outbound.order.FindAllOrdersPort;
import com.snackbar.application.ports.outbound.order.FindOrderByIdPort;
import com.snackbar.application.ports.outbound.order.FindsOrdersByStatusPort;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class FindOrderAdapter implements FindAllOrdersPort, FindsOrdersByStatusPort, FindOrderByIdPort {

    private final OrderRepository repository;

    public FindOrderAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<Order> findAllOrders() {
        return this.repository.findAll().stream().map(OrderJpaEntity::toAggregate).toList();
    }

    @Override
    @Transactional
    public List<Order> findOrdersByStatus(OrderStatus status) {
        OrderJpaEntity orderJpaEntity = OrderJpaEntity.from(status);
        Example<OrderJpaEntity> example = Example.of(orderJpaEntity);
        return this.repository.findAll(example).stream().map(OrderJpaEntity::toAggregate).toList();
    }

    @Override
    @Transactional
    public Optional<Order> findOrderById(OrderId orderId) {
        return this.repository.findById(orderId.getValue()).map(OrderJpaEntity::toAggregate);
    }
}
