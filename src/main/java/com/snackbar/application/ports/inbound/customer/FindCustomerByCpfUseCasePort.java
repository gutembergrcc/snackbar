package com.snackbar.application.ports.inbound.customer;

import com.snackbar.application.core.domain.customer.Customer;

import java.util.Optional;

public interface FindCustomerByCpfUseCasePort {

    Optional<Customer> execute(String cpf);
}
