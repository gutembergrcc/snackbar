package com.snackbar.application.ports.inbound.product;

import com.snackbar.application.core.domain.product.ProductId;

public interface DeleteProductUseCasePort {

    void execute(ProductId productId);
}
