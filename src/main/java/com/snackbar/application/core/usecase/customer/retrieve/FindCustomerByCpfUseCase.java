package com.snackbar.application.core.usecase.customer.retrieve;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.usecase.customer.CustomerOutput;
import com.snackbar.application.ports.inbound.customer.FindCustomerByCpfUseCasePort;
import com.snackbar.application.ports.outbound.customer.FindCustomerByCpfPort;

import java.util.Optional;

public class FindCustomerByCpfUseCase implements FindCustomerByCpfUseCasePort {

    private final FindCustomerByCpfPort findCustomerByCpfPort;

    public FindCustomerByCpfUseCase(FindCustomerByCpfPort findCustomerByCpfPort) {
        this.findCustomerByCpfPort = findCustomerByCpfPort;
    }

    @Override
    public CustomerOutput execute(String cpf) {
        Optional<Customer> customerByCpf = this.findCustomerByCpfPort.findCustomerByCpf(cpf);

        if(customerByCpf.isPresent()){
            return CustomerOutput.from(customerByCpf.get());
        }
        return null;
    }
}
