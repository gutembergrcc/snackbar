package com.snackbar.application.ports.inbound.product;

import com.snackbar.application.core.domain.product.Product;

public interface CreateProductUserCasePort {

    Product execute(Product product);
}
