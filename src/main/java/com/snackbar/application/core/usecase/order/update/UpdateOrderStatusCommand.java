package com.snackbar.application.core.usecase.order.update;

import com.snackbar.application.core.domain.order.OrderStatus;

public record UpdateOrderStatusCommand(String orderId,
                                       OrderStatus status) {

    public static UpdateOrderStatusCommand with(
            final String orderId,
            final OrderStatus status
    ) {
        return new UpdateOrderStatusCommand(orderId, status);
    }
}
