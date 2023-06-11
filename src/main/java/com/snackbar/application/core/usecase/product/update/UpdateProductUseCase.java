package com.snackbar.application.core.usecase.product.update;

import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.validation.Notification;
import com.snackbar.application.ports.inbound.product.UpdateProductUseCasePort;
import com.snackbar.application.ports.outbound.product.UpdateProductPort;

public class UpdateProductUseCase implements UpdateProductUseCasePort {

    private final UpdateProductPort updateProductPort;

    public UpdateProductUseCase(UpdateProductPort updateProductPort) {
        this.updateProductPort = updateProductPort;
    }

    @Override
    public Product execute(Product product) {
        final var notification = Notification.create();
        product.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        return this.updateProductPort.update(product);
    }
}
