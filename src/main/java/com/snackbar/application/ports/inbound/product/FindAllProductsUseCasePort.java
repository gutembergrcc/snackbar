package com.snackbar.application.ports.inbound.product;

import com.snackbar.application.core.domain.product.Product;

import java.util.List;

public interface FindAllProductsUseCasePort {

    List<Product> execute();
}
