package com.snackbar.adapters.inbound.rest.product.mapper;

import com.snackbar.adapters.inbound.rest.product.models.ProductResponse;
import com.snackbar.application.core.usecase.product.ProductOutput;

public interface ProductMapper {

    static ProductResponse toProductResponse(final ProductOutput output) {
        return new ProductResponse(output.id(),
                output.name(), output.price(), output.description(), output.category());
    }
}
