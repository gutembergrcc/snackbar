package com.snackbar.adapters.inbound.rest.customer.mapper;

import com.snackbar.adapters.inbound.rest.customer.models.CustomerResponse;
import com.snackbar.adapters.outbound.persistence.customer.repository.CustomerJpaEntity;
import com.snackbar.application.core.domain.customer.Customer;

import java.util.Optional;

public interface CustomerMapper {

    static CustomerResponse toConsumerResponse(final Customer customer) {
        return new CustomerResponse(customer.getId().getValue(),
                customer.getFirstName(), customer.getLastName(), customer.getCpf());
    }
}
