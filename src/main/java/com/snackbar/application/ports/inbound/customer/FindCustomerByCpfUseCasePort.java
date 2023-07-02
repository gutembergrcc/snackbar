package com.snackbar.application.ports.inbound.customer;

import com.snackbar.application.core.usecase.customer.CustomerOutput;

public interface FindCustomerByCpfUseCasePort {

    CustomerOutput execute(String cpf);
}
