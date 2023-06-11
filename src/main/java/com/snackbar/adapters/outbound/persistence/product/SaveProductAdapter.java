package com.snackbar.adapters.outbound.persistence.product;

import com.snackbar.adapters.outbound.persistence.product.repository.ProductJpaEntity;
import com.snackbar.adapters.outbound.persistence.product.repository.ProductRepository;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.ports.outbound.product.SaveProductPort;
import org.springframework.stereotype.Component;

@Component
public class SaveProductAdapter implements SaveProductPort {

    private final ProductRepository repository;

    public SaveProductAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return this.repository.save(ProductJpaEntity.from(product)).toAggregate();
    }
}
