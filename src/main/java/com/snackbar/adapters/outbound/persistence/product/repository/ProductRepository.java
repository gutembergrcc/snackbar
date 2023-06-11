package com.snackbar.adapters.outbound.persistence.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, String> {

    @Query(value = "select p.id from Product p where p.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);
}
