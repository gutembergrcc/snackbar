package com.snackbar.adapters.outbound.persistence.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerJpaEntity, String> {

}
