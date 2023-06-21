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

    static CustomerResponse entityToConsumerResponse(final Optional<CustomerJpaEntity> entity) {
        return new CustomerResponse(entity.get().getId(),
                entity.get().getFirstName(), entity.get().getLastName(), entity.get().getCpf());
    }
}
