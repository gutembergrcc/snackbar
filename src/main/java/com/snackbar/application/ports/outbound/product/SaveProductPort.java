package com.snackbar.application.ports.outbound.product;

import com.snackbar.application.core.domain.product.Product;

public interface SaveProductPort {

    Product save(Product product);
}
