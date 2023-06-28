package com.snackbar.application.core.domain.customer;

import com.snackbar.application.core.domain.Entity;
import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.core.domain.validation.ValidationHandler;

public class Customer extends Entity<CustomerId> {

    private String firstName;
    private String lastName;
    private String cpf;
    private OrderId orderId;

    private Customer(CustomerId customerId, String firstName, String lastName, String cpf, OrderId orderId) {
        super(customerId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.orderId = orderId;
    }

    public static Customer newCustomer(final String firstName, final String lastName, final String cpf, final OrderId orderId) {
        final var id = CustomerId.unique();
        return new Customer(id, firstName, lastName, cpf, orderId);
    }

    public static Customer with(final CustomerId customerId, final String firstName, final String lastName, final String cpf, final OrderId orderId) {
        return new Customer(customerId, firstName, lastName, cpf, orderId);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CustomerValidator(this, handler).validate();
    }
}
