package com.snackbar.application.ports.outbound.customer;

import com.snackbar.application.core.domain.customer.Customer;

import java.util.Optional;

public interface FindCustomerByCpfPort {

    Optional<Customer> findCustomerByCpf(String cpf);
}
