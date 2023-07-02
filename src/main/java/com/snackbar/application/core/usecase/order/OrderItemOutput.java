package com.snackbar.application.core.usecase.order;

import com.snackbar.application.core.domain.order.OrderItem;
import com.snackbar.application.core.domain.product.Product;

public record OrderItemOutput(String id,
                              String productId,
                              String productName,
                              Integer quantity) {

    public static OrderItemOutput from(final OrderItem orderItem) {
        Product product = orderItem.getProduct();
        return new OrderItemOutput(orderItem.getId().getValue(),
                product.getId().getValue(), product.getName(), orderItem.getQuantity());
    }
}
