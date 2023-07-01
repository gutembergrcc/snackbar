package com.snackbar.adapters.outbound.persistence.product;

import com.snackbar.adapters.outbound.persistence.product.repository.ProductJpaEntity;
import com.snackbar.adapters.outbound.persistence.product.repository.ProductRepository;
import com.snackbar.application.core.domain.product.Category;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.product.ProductId;
import com.snackbar.application.ports.outbound.product.FindAllProductsPort;
import com.snackbar.application.ports.outbound.product.FindProductByIdPort;
import com.snackbar.application.ports.outbound.product.FindProductsByCategoryPort;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindProductAdapter implements FindProductsByCategoryPort, FindAllProductsPort, FindProductByIdPort {

    private final ProductRepository repository;

    public FindProductAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findProductsByCategory(Category category) {
        ProductJpaEntity productJpaEntity = ProductJpaEntity.from(category);
        Example<ProductJpaEntity> example = Example.of(productJpaEntity);
        return this.repository.findAll(example).stream().map(ProductJpaEntity::toAggregate).toList();
    }

    @Override
    public List<Product> findAllProducts() {
        return this.repository.findAll().stream().map(ProductJpaEntity::toAggregate).toList();
    }

    @Override
    public Optional<Product> findProductById(ProductId productId) {
        return this.repository.findById(productId.getValue()).map(ProductJpaEntity::toAggregate);
    }
}
