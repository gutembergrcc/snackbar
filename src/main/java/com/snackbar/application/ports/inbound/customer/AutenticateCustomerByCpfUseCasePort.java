package com.snackbar.application.ports.inbound.customer;

import com.snackbar.application.core.usecase.customer.CustomerOutput;

public interface AutenticateCustomerByCpfUseCasePort {
    CustomerOutput execute(String cpf);
}
