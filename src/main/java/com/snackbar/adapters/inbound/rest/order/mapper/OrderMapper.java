package com.snackbar.adapters.inbound.rest.order.mapper;

import com.snackbar.adapters.inbound.rest.order.models.OrderResponse;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.usecase.order.OrderOutput;

public interface OrderMapper {
    static OrderResponse toOrderResponse(OrderOutput order) {
        var items = order.items().stream().map(OrderItemMapper::toOrderItemResponse).toList();
        return new OrderResponse(order.id(), order.ticket(), items, order.observation(), order.status().getName());
    }
}
