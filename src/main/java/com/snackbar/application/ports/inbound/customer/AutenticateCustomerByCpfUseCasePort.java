package com.snackbar.application.ports.inbound.customer;

import com.snackbar.application.core.domain.customer.Customer;

import java.util.Optional;

public interface AutenticateCustomerByCpfUseCasePort {
    Optional<Customer> execute(String cpf);
}
