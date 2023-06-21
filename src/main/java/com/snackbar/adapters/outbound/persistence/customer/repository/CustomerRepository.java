package com.snackbar.adapters.outbound.persistence.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerJpaEntity, String> {

    @Query(nativeQuery = true, value = "select * from customers where cpf = :cpf")
    Optional<CustomerJpaEntity> findCustomerByCpf(String cpf);
}
