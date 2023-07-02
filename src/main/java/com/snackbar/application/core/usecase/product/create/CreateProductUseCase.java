package com.snackbar.application.core.usecase.product.create;

import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.validation.Notification;
import com.snackbar.application.core.usecase.product.ProductOutput;
import com.snackbar.application.ports.inbound.product.CreateProductUseCasePort;
import com.snackbar.application.ports.outbound.product.SaveProductPort;

public class CreateProductUseCase implements CreateProductUseCasePort {

    private final SaveProductPort saveProductPort;

    public CreateProductUseCase(SaveProductPort saveProductPort) {
        this.saveProductPort = saveProductPort;
    }

    @Override
    public ProductOutput execute(CreateProductCommand command) {
        Product newProduct = Product.newProduct(command.name(), command.price(), command.description(), command.category());
        final var notification = Notification.create();
        newProduct.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        return ProductOutput.from(this.saveProductPort.save(newProduct));
    }
}

