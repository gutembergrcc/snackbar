package com.snackbar.application.ports.outbound.customer;

import com.snackbar.application.core.domain.customer.Customer;

public interface SaveCustomerPort {

    Customer save(Customer product);
}
