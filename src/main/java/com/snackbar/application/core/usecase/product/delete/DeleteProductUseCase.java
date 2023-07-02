package com.snackbar.application.core.usecase.product.delete;

import com.snackbar.application.core.domain.product.ProductId;
import com.snackbar.application.ports.inbound.product.DeleteProductUseCasePort;
import com.snackbar.application.ports.outbound.product.DeleteProductPort;


public class DeleteProductUseCase implements DeleteProductUseCasePort  {

    private final DeleteProductPort deleteProductPort;

    public DeleteProductUseCase(DeleteProductPort deleteProductPort) {
        this.deleteProductPort = deleteProductPort;
    }

    @Override
    public void execute(String productId) {
        this.deleteProductPort.deleteById(ProductId.from(productId));
    }
}
