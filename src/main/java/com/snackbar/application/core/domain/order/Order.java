package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.Entity;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.utils.InstantUtils;
import com.snackbar.application.core.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.List;

public class Order extends Entity<OrderId> {

    private final String ticket;
    private final List<OrderItem> items;
    private final Customer customer;
    private final String observation;
    private final OrderStatus status;
    private final Instant createdAt;

    private Order(final OrderId orderId,
                  final String ticket,
                  final List<OrderItem> items,
                  final Customer customer,
                  final String observation,
                  final OrderStatus status,
                  final Instant creationDate) {
        super(orderId);
        this.ticket = ticket;
        this.items = items;
        this.customer = customer;
        this.observation = observation;
        this.status = status;
        this.createdAt = creationDate;
    }

    public static Order newOrder(final String ticket, final List<OrderItem> items, final Customer customer, final String observation) {
        final var id = OrderId.unique();
        final var now = InstantUtils.now();
        return new Order(id, ticket, items, customer, observation, OrderStatus.RECEIVED, now);
    }

    public static Order with(final OrderId orderId,
                             final String ticket,
                             final List<OrderItem> items,
                             final Customer customer,
                             final String observation,
                             final OrderStatus status,
                             final Instant createdAt) {
        return new Order(orderId, ticket, items, customer, observation, status, createdAt);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new OrderValidator(this, handler).validate();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getObservation() {
        return observation;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getTicket() {
        return ticket;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
