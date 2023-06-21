package com.snackbar.application.core.usecase.customer.retrieve;

import com.snackbar.adapters.outbound.persistence.customer.repository.CustomerJpaEntity;
import com.snackbar.application.ports.inbound.customer.FindCustomerByCpfUseCasePort;
import com.snackbar.application.ports.outbound.customer.FindCustomerByCpfPort;

import java.util.Optional;

public class FindCustomerByCpfUseCase implements FindCustomerByCpfUseCasePort {

    private final FindCustomerByCpfPort findCustomerByCpfPort;

    public FindCustomerByCpfUseCase(FindCustomerByCpfPort findCustomerByCpfPort) {
        this.findCustomerByCpfPort = findCustomerByCpfPort;
    }

    @Override
    public Optional<CustomerJpaEntity> execute(String cpf) {
        return this.findCustomerByCpfPort.findCustomerByCpf(cpf);

    }
}
