package com.snackbar.adapters.inbound.rest.product.mapper;

import com.snackbar.adapters.inbound.rest.product.models.ProductResponse;
import com.snackbar.application.core.domain.product.Product;

public interface ProductMapper {

    static ProductResponse toProductResponse(final Product product) {
        return new ProductResponse(product.getId().getValue(),
                product.getName(), product.getPrice(), product.getQuantity(), product.getDescription(), product.getCategory());
    }
}
