package com.snackbar.application.core.usecase.product.retrieve;

import com.snackbar.application.core.usecase.product.ProductOutput;
import com.snackbar.application.ports.inbound.product.FindAllProductsUseCasePort;
import com.snackbar.application.ports.outbound.product.FindAllProductsPort;

import java.util.List;

public class FindAllProductsUseCase implements FindAllProductsUseCasePort {

    private final FindAllProductsPort findAllProductsPort;

    public FindAllProductsUseCase(FindAllProductsPort findAllProductsPort) {
        this.findAllProductsPort = findAllProductsPort;
    }

    @Override
    public List<ProductOutput> execute() {
        return this.findAllProductsPort.findAllProducts().stream().map(ProductOutput::from).toList();
    }
}
