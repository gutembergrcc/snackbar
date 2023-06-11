package com.snackbar.application.ports.inbound.customer;

import com.snackbar.application.core.domain.customer.Customer;

import java.util.List;

public interface FindAllCustomersUseCasePort {

    List<Customer> execute();
}
