package com.snackbar.adapters.inbound.rest.order.mapper;

import com.snackbar.adapters.inbound.rest.order.models.OrderResponse;
import com.snackbar.application.core.domain.order.Order;

public interface OrderMapper {
    static OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(order.getId().getValue(), order.getTicket(), order.getStatus().getName());
    }
}
