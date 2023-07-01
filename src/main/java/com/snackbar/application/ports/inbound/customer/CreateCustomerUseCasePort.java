package com.snackbar.application.ports.inbound.customer;

import com.snackbar.application.core.domain.customer.Customer;

public interface CreateCustomerUseCasePort {

    Customer execute(Customer customer);
}
