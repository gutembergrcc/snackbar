package com.snackbar.application.ports.inbound.product;

import com.snackbar.application.core.usecase.product.ProductOutput;
import com.snackbar.application.core.usecase.product.update.UpdateProductCommand;

public interface UpdateProductUseCasePort {

    ProductOutput execute(UpdateProductCommand command);
}
