package com.snackbar.application.ports.inbound.product;

import com.snackbar.application.core.usecase.product.ProductOutput;

import java.util.List;

public interface FindAllProductsUseCasePort {

    List<ProductOutput> execute();
}
