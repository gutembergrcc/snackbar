package com.snackbar.adapters.outbound.persistence.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, String> {
}
