package com.snackbar.application.core.usecase.customer.create;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.validation.Notification;
import com.snackbar.application.ports.inbound.customer.CreateCustomerUserCasePort;
import com.snackbar.application.ports.outbound.customer.SaveCustomerPort;

public class CreateCustomerUseCase implements CreateCustomerUserCasePort {

    private final SaveCustomerPort saveCustomerPort;

    public CreateCustomerUseCase(SaveCustomerPort saveCustomerPort) {
        this.saveCustomerPort = saveCustomerPort;
    }

    @Override
    public Customer execute(Customer customer) {


        final var notification = Notification.create();
        customer.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        return this.saveCustomerPort.save(customer);
    }
}

