package com.snackbar.application.core.usecase.customer.create;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.validation.Notification;
import com.snackbar.application.core.usecase.customer.CustomerOutput;
import com.snackbar.application.ports.inbound.customer.CreateCustomerUseCasePort;
import com.snackbar.application.ports.outbound.customer.SaveCustomerPort;

public class CreateCustomerUseCase implements CreateCustomerUseCasePort {

    private final SaveCustomerPort saveCustomerPort;

    public CreateCustomerUseCase(SaveCustomerPort saveCustomerPort) {
        this.saveCustomerPort = saveCustomerPort;
    }

    @Override
    public CustomerOutput execute(CreateCustomerCommand command) {
        String cpfDigitOnly = command.cpf().replaceAll("\\D", "");
        Customer newCustomer = Customer.newCustomer(command.firstName(), command.lastName(), cpfDigitOnly);

        final var notification = Notification.create();
        newCustomer.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        Customer customer = this.saveCustomerPort.save(newCustomer);
        return CustomerOutput.from(customer);
    }
}

