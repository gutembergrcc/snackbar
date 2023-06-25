package com.snackbar.adapters.outbound.persistence.order.repository;

import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.core.domain.order.Status;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Order")
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "product_id")
    private String product;

    @Column(name = "customer_id")
    private String customer;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status;

    public OrderJpaEntity() {
    }

    public OrderJpaEntity(String id, String product, String customer, String description, Status status) {
        this.id = id;
        this.product = product;
        this.customer = customer;
        this.description = description;
        this.status = status;
    }

    public static OrderJpaEntity from(final Order order) {
        return new OrderJpaEntity(order.getId().getValue(), order.getProduct(), order.getCustomer(), order.getDescription(), order.getStatus());
    }

    public static OrderJpaEntity from(final Status status) {
        return new OrderJpaEntity(null, null, null, null, status);
    }

    public Order toAggregate() {
        return Order.with(OrderId.from(getId()), getProduct(), getCustomer(), getDescription(), getStatus());
    }

    public String getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public String getCustomer() {
        return customer;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }
}
