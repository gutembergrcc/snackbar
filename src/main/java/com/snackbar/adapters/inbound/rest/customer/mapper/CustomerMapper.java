package com.snackbar.adapters.inbound.rest.customer.mapper;

import com.snackbar.adapters.inbound.rest.customer.models.CustomerResponse;
import com.snackbar.application.core.usecase.customer.CustomerOutput;

public interface CustomerMapper {

    static CustomerResponse toConsumerResponse(final CreateCustomerOutput createCustomerOutput) {
        return new CustomerResponse(createCustomerOutput.id(),
                createCustomerOutput.firstName(), createCustomerOutput.lastName(), createCustomerOutput.cpf());
    }

    static CustomerResponse toConsumerResponse(final CustomerOutput customerOutput) {
        return new CustomerResponse(customerOutput.id(),
                customerOutput.firstName(), customerOutput.lastName(), customerOutput.cpf());
    }
}
