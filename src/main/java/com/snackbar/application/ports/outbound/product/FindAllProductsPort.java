package com.snackbar.application.ports.outbound.product;

import com.snackbar.application.core.domain.product.Product;

import java.util.List;

public interface FindAllProductsPort {

    List<Product> findAllProducts();
}
