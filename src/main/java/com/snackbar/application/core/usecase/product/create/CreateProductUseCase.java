package com.snackbar.application.core.usecase.product.create;

import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.validation.Notification;
import com.snackbar.application.ports.inbound.product.CreateProductUserCasePort;
import com.snackbar.application.ports.outbound.product.SaveProductPort;

public class CreateProductUseCase implements CreateProductUserCasePort {

    private final SaveProductPort saveProductPort;

    public CreateProductUseCase(SaveProductPort saveProductPort) {
        this.saveProductPort = saveProductPort;
    }

    @Override
    public Product execute(Product product) {

        final var notification = Notification.create();
        product.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        return this.saveProductPort.save(product);
    }
}

