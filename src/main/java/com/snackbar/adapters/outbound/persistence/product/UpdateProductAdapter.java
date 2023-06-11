package com.snackbar.adapters.outbound.persistence.product;

import com.snackbar.adapters.outbound.persistence.product.repository.ProductJpaEntity;
import com.snackbar.adapters.outbound.persistence.product.repository.ProductRepository;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.ports.outbound.product.UpdateProductPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductAdapter implements UpdateProductPort {

    private final ProductRepository repository;

    public UpdateProductAdapter(ProductRepository repository) {
        this.repository = repository;
    }
    @Override
    public Product update(Product product) {
        return this.repository.save(ProductJpaEntity.from(product)).toAggregate();
    }
}
