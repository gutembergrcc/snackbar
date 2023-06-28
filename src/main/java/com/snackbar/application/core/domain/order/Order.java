package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.Entity;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.validation.ValidationHandler;

import java.util.List;

public class Order extends Entity<OrderId> {

    private List<Product> product;

    private Customer customer;
    private String description;
    private Status status;

    private Order(final OrderId id, final Customer customer, final String description, final Status status) {
        super(id);
        this.customer = customer;
        this.description = description;
        this.status = status;
    }

    public static Order newOrder(final Customer customer, final String description, final Status status) {
        final var id = OrderId.unique();
        return new Order(id, customer, description, status);
    }

    public static Order with(final OrderId orderId, final Customer customer, final String description, final Status status) {
        return new Order(orderId, customer, description, status);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new OrderValidator(this, handler).validate();
    }

    public List<Product> getProduct() {return product;}

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
