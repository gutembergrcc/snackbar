package com.snackbar.adapters.outbound.persistence.product;

import com.snackbar.adapters.outbound.persistence.product.repository.ProductRepository;
import com.snackbar.application.core.domain.product.ProductId;
import com.snackbar.application.ports.outbound.product.DeleteProductPort;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductAdapter implements DeleteProductPort {

    private final ProductRepository repository;

    public DeleteProductAdapter(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public void deleteById(ProductId productId) {
        if (productId != null && this.repository.existsById(productId.getValue())) {
            this.repository.deleteById(productId.getValue());
        }
    }
}
