package com.snackbar.adapters.outbound.persistence.order.repository;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.core.domain.order.Status;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Order")
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status;

    public OrderJpaEntity() {
    }

    public OrderJpaEntity(String id, Customer customer, String description, Status status) {
        this.id = id;
        this.customer = customer;
        this.description = description;
        this.status = status;
    }

    public static OrderJpaEntity from(final Order order) {
        return new OrderJpaEntity(order.getId().getValue(), order.getCustomer(), order.getDescription(), order.getStatus());
    }

    public static OrderJpaEntity from(final Status status) {
        return new OrderJpaEntity(null, null, null, status);
    }

    public Order toAggregate() {
        return Order.with(OrderId.from(getId()), getCustomer(), getDescription(), getStatus());
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }
}
