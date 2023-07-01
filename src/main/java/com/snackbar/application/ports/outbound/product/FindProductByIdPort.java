package com.snackbar.application.ports.outbound.product;

import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.product.ProductId;

import java.util.Optional;

public interface FindProductByIdPort {

    Optional<Product> findProductById(ProductId productId);
}
