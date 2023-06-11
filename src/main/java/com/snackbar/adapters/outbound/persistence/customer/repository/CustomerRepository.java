package com.snackbar.adapters.outbound.persistence.customer.repository;

import com.snackbar.adapters.outbound.persistence.product.repository.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerJpaEntity, String> {

}
