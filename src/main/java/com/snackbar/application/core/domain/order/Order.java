package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.Entity;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.validation.ValidationHandler;

public class Order extends Entity<OrderId> {

    private Customer customer;
    private Status status;
    private Product product;

    private Order(final OrderId id, final Customer customer, final Status status, final Product product) {
        super(id);
        this.customer = customer;
        this.status = status;
        this.product = product;
    }

    public static Order newOrder(final Customer customer, final Status status, final Product product) {
        final var id = OrderId.unique();
        return new Order(id, customer, status, product);
    }

    public static Order with(final OrderId orderId, final Customer customer, final Status status, final Product product) {
        return new Order(orderId, customer, status, product);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new OrderValidator(this, handler).validate();
    }

    public Product getProduct() {return product;}

    public Customer getCustomer() {
        return customer;
    }

    public Status getStatus() {
        return status;
    }
}
