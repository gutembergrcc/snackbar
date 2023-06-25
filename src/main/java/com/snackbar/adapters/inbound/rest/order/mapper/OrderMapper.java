package com.snackbar.adapters.inbound.rest.order.mapper;

import com.snackbar.adapters.inbound.rest.order.models.OrderResponse;
import com.snackbar.application.core.domain.order.Order;

public interface OrderMapper {

    static OrderResponse toOrderResponse(final Order order) {
        return new OrderResponse(order.getId().getValue(),
                order.getProduct(), order.getCustomer(), order.getDescription(), order.getStatus());
    }
}
