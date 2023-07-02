package com.snackbar.application.ports.inbound.product;

import com.snackbar.application.core.usecase.product.ProductOutput;
import com.snackbar.application.core.usecase.product.create.CreateProductCommand;

public interface CreateProductUseCasePort {

    ProductOutput execute(CreateProductCommand product);
}
