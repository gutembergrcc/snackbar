package com.snackbar.application.ports.outbound.customer;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.customer.CustomerId;

import java.util.Optional;

public interface FindCustomerByIdPort {

    Optional<Customer> findCustomerById(CustomerId customerId);
}
