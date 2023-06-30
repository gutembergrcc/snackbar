package com.snackbar.adapters.inbound.rest.order.mapper;

import com.snackbar.adapters.inbound.rest.order.models.OrderResponse;
import com.snackbar.application.core.domain.order.Order;

import java.util.Collections;

public interface OrderMapper {

    static OrderResponse toOrderResponse(final Order order) {
        return new OrderResponse(order.getId().getValue(), order.getCustomer(), order.getStatus(), Collections.singletonList(order.getProduct()));
    }
}
