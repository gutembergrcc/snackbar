package com.snackbar.application.ports.inbound.product;

import com.snackbar.application.core.domain.product.Product;

public interface UpdateProductUseCasePort {

    Product execute(Product product);
}
