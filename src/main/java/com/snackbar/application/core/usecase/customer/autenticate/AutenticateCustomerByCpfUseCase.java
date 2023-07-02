package com.snackbar.application.core.usecase.customer.autenticate;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.exceptions.ErrorName;
import com.snackbar.application.core.domain.validation.Error;
import com.snackbar.application.core.usecase.customer.CustomerOutput;
import com.snackbar.application.ports.inbound.customer.AutenticateCustomerByCpfUseCasePort;
import com.snackbar.application.ports.outbound.customer.FindCustomerByCpfPort;

import java.util.Optional;

public class AutenticateCustomerByCpfUseCase implements AutenticateCustomerByCpfUseCasePort {

    private final FindCustomerByCpfPort findCustomerByCpfPort;

    public AutenticateCustomerByCpfUseCase(FindCustomerByCpfPort findCustomerByCpfPort) {
        this.findCustomerByCpfPort = findCustomerByCpfPort;
    }

    @Override
    public CustomerOutput execute(String cpf) {
        Optional<Customer> customer = this.findCustomerByCpfPort.findCustomerByCpf(cpf);
        if(customer.isEmpty()){
            throw DomainException.with(new Error(ErrorName.CUSTOMER_WITHOUT_ACESS));
        }
        return CustomerOutput.from(customer.get());
    }
}
