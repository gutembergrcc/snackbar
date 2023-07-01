package com.snackbar.adapters.outbound.persistence.order.repository;

import com.snackbar.adapters.outbound.persistence.customer.repository.CustomerJpaEntity;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.core.domain.order.OrderStatus;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Order")
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "ticket", nullable = false)
    private String ticket;

    @OneToMany(mappedBy = "orderJpaEntity", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private List<OrderItemJpaEntity> items;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerJpaEntity customerJpaEntity;

    @Column(name = "observation")
    private String observation;

    @Column(name = "status", nullable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    public OrderJpaEntity() {
    }

    public OrderJpaEntity(String id,
                          String ticket,
                          List<OrderItemJpaEntity> items,
                          CustomerJpaEntity customerJpaEntity,
                          String observation,
                          OrderStatus orderStatus,
                          Instant createdAt) {
        this.id = id;
        this.ticket = ticket;
        this.items = items;
        this.customerJpaEntity = customerJpaEntity;
        this.observation = observation;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    public static OrderJpaEntity from(final Order order) {
        CustomerJpaEntity customerJpa = CustomerJpaEntity.from(order.getCustomer());

        OrderJpaEntity orderJpaEntity = new OrderJpaEntity(
                order.getId().getValue(),
                order.getTicket(),
                new ArrayList<>(),
                customerJpa,
                order.getObservation(),
                order.getStatus(),
                order.getCreatedAt());

        order.getItems().stream().map(orderItem -> OrderItemJpaEntity.from(orderJpaEntity, orderItem)).forEach(orderJpaEntity.getItems()::add);

        return orderJpaEntity;
    }

    public Order toAggregate() {
        var orderItems = getItems().stream().map(OrderItemJpaEntity::toAggregate).toList();
        return Order.with(
                OrderId.from(getId()),
                getTicket(),
                orderItems,
                getCustomerJpaEntity().toAggregate(),
                getObservation(),
                getOrderStatus(),
                getCreatedAt());
    }

    public String getId() {
        return id;
    }

    public String getTicket() {
        return ticket;
    }

    public List<OrderItemJpaEntity> getItems() {
        return items;
    }

    public CustomerJpaEntity getCustomerJpaEntity() {
        return customerJpaEntity;
    }

    public String getObservation() {
        return observation;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
