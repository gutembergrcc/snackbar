package com.snackbar.application.core.usecase.product.retrieve;

import com.snackbar.application.core.domain.product.Category;
import com.snackbar.application.core.usecase.product.ProductOutput;
import com.snackbar.application.ports.inbound.product.FindProductsByCategoryUseCasePort;
import com.snackbar.application.ports.outbound.product.FindProductsByCategoryPort;

import java.util.List;

public class FindProductsByCategoryUseCase implements FindProductsByCategoryUseCasePort {

    private final FindProductsByCategoryPort findProductsByCategoryPort;

    public FindProductsByCategoryUseCase(FindProductsByCategoryPort findProductsByCategoryPort) {
        this.findProductsByCategoryPort = findProductsByCategoryPort;
    }

    @Override
    public List<ProductOutput> execute(Category category) {
        return this.findProductsByCategoryPort.findProductsByCategory(category).stream().map(ProductOutput::from).toList();
    }
}
