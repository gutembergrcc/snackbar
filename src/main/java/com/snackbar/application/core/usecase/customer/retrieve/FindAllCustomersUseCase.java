package com.snackbar.application.core.usecase.customer.retrieve;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.ports.inbound.customer.FindAllCustomersUseCasePort;
import com.snackbar.application.ports.outbound.customer.FindAllCustomersPort;

import java.util.List;

public class FindAllCustomersUseCase implements FindAllCustomersUseCasePort {

    private final FindAllCustomersPort findAllCustomersPort;

    public FindAllCustomersUseCase(FindAllCustomersPort findAllCustomersPort) {
        this.findAllCustomersPort = findAllCustomersPort;
    }

    @Override
    public List<Customer> execute() {
        return this.findAllCustomersPort.findAllCustomers();
    }
}
