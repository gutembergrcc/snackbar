package com.snackbar.application.ports.inbound.customer;

import com.snackbar.adapters.outbound.persistence.customer.repository.CustomerJpaEntity;

import java.util.Optional;

public interface FindCustomerByCpfUseCasePort {

    Optional<CustomerJpaEntity> execute(String cpf);
}
