package com.snackbar.adapters.outbound.persistence.customer;

import com.snackbar.adapters.outbound.persistence.customer.repository.CustomerJpaEntity;
import com.snackbar.adapters.outbound.persistence.customer.repository.CustomerRepository;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.ports.outbound.customer.SaveCustomerPort;
import org.springframework.stereotype.Component;

@Component
public class SaveCustomerAdapter implements SaveCustomerPort {

    private final CustomerRepository repository;

    public SaveCustomerAdapter(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        return this.repository.save(CustomerJpaEntity.from(customer)).toAggregate();
    }
}
