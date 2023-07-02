package com.snackbar.application.ports.inbound.product;

import com.snackbar.application.core.domain.product.Category;
import com.snackbar.application.core.usecase.product.ProductOutput;

import java.util.List;

public interface FindProductsByCategoryUseCasePort {

    List<ProductOutput> execute(Category category);
}
