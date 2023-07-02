package com.snackbar.application.ports.inbound.customer;

import com.snackbar.application.core.usecase.customer.CustomerOutput;

import java.util.List;

public interface FindAllCustomersUseCasePort {

    List<CustomerOutput> execute();
}
