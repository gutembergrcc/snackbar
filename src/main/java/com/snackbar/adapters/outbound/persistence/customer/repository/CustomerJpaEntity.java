package com.snackbar.adapters.outbound.persistence.customer.repository;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.customer.CustomerId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Customer")
@Table(name = "customers")
public class CustomerJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    public CustomerJpaEntity() {
    }

    public CustomerJpaEntity(String id, String firstName, String lastName, String cpf) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public static CustomerJpaEntity from(final Customer customer) {
        return new CustomerJpaEntity(customer.getId().getValue(), customer.getFirstName(), customer.getLastName(), customer.getCpf());
    }

    public Customer toAggregate() {
        return Customer.with(CustomerId.from(getId()), getFirstName(), getLastName(), getCpf());
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }
}
