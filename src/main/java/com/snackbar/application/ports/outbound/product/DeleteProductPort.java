package com.snackbar.application.ports.outbound.product;

import com.snackbar.application.core.domain.product.ProductId;

public interface DeleteProductPort {

    void deleteById(ProductId productId);
}
