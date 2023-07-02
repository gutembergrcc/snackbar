package com.snackbar.application.ports.inbound.customer;

import com.snackbar.application.core.usecase.customer.CustomerOutput;
import com.snackbar.application.core.usecase.customer.create.CreateCustomerCommand;

public interface CreateCustomerUseCasePort {

    CustomerOutput execute(CreateCustomerCommand customer);
}
