package com.snackbar.application.ports.outbound.customer;

import com.snackbar.application.core.domain.customer.Customer;

import java.util.List;

public interface FindAllCustomersPort {

    List<Customer> findAllCustomers();
}
