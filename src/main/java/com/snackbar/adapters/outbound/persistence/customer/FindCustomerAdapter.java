package com.snackbar.adapters.outbound.persistence.customer;

import com.snackbar.adapters.outbound.persistence.customer.repository.CustomerJpaEntity;
import com.snackbar.adapters.outbound.persistence.customer.repository.CustomerRepository;
import com.snackbar.adapters.outbound.persistence.product.repository.ProductJpaEntity;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.ports.outbound.customer.FindAllCustomersPort;
import com.snackbar.application.ports.outbound.customer.FindCustomerByCpfPort;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindCustomerAdapter implements FindAllCustomersPort, FindCustomerByCpfPort {

    private final CustomerRepository repository;

    public FindCustomerAdapter(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return this.repository.findAll().stream().map(CustomerJpaEntity::toAggregate).toList();
    }

    @Override
    public Optional<Customer> findCustomerByCpf(String cpf) {
        CustomerJpaEntity customerJpaEntity = CustomerJpaEntity.from(cpf);
        Example<CustomerJpaEntity> example = Example.of(customerJpaEntity);
        return this.repository.findOne(example).map(CustomerJpaEntity::toAggregate);
    }
}
