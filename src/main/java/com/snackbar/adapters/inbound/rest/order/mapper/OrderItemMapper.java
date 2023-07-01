package com.snackbar.adapters.inbound.rest.order.mapper;

import com.snackbar.adapters.inbound.rest.order.models.OrderItemRequest;
import com.snackbar.application.core.usecase.order.create.CreateOrderItemCommand;

import java.util.List;

public interface OrderItemMapper {

    static List<CreateOrderItemCommand> toOrderItem(List<OrderItemRequest> itemsRequest) {
        return itemsRequest.stream().map(
                itemRequest -> CreateOrderItemCommand.with(itemRequest.productId(), itemRequest.quantity()))
                .toList();
    }
}
