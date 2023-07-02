package com.snackbar.adapters.inbound.rest.order.mapper;

import com.snackbar.adapters.inbound.rest.customer.models.CustomerResponse;
import com.snackbar.adapters.inbound.rest.order.models.OrderItemRequest;
import com.snackbar.adapters.inbound.rest.order.models.OrderItemResponse;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.order.OrderItem;
import com.snackbar.application.core.usecase.order.OrderItemOutput;
import com.snackbar.application.core.usecase.order.create.CreateOrderItemCommand;

import java.util.List;

public interface OrderItemMapper {

    static List<CreateOrderItemCommand> toOrderItemCommand(List<OrderItemRequest> itemsRequest) {
        return itemsRequest.stream().map(
                itemRequest -> CreateOrderItemCommand.with(itemRequest.productId(), itemRequest.quantity()))
                .toList();
    }

    static OrderItemResponse toOrderItemResponse(final OrderItemOutput orderItem) {
        return new OrderItemResponse(orderItem.id(),
                orderItem.productId(),
                orderItem.productName(),
                orderItem.quantity());
    }
}
