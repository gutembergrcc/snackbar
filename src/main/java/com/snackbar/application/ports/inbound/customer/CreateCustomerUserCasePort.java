package com.snackbar.application.ports.inbound.customer;

import com.snackbar.application.core.domain.customer.Customer;

public interface CreateCustomerUserCasePort {

    Customer execute(Customer customer);
}
