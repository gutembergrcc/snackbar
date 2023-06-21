package com.snackbar.application.ports.outbound.customer;

import com.snackbar.adapters.outbound.persistence.customer.repository.CustomerJpaEntity;

import java.util.Optional;

public interface FindCustomerByCpfPort {

    Optional<CustomerJpaEntity> findCustomerByCpf(String cpf);
}
