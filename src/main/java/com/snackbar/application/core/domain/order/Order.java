package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.Entity;
import com.snackbar.application.core.domain.validation.ValidationHandler;

public class Order extends Entity<OrderId> {

    private String product;

    private String customer;
    private String description;
    private Status status;

    private Order(final OrderId id, final String product, final String customer, final String description, final Status status) {
        super(id);
        this.product = product;
        this.customer = customer;
        this.description = description;
        this.status = status;
    }

    public static Order newOrder(final String product, final String customer, final String description, final Status status) {
        final var id = OrderId.unique();
        return new Order(id, product, customer, description, status);
    }

    public static Order with(final OrderId orderId, final String product, final String customer, final String description, final Status status) {
        return new Order(orderId, product, customer, description, status);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new OrderValidator(this, handler).validate();
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
