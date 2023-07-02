package com.snackbar.application.ports.outbound.order;

import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.OrderId;

import java.util.Optional;

public interface FindOrderByIdPort {

    Optional<Order> findOrderById(OrderId orderId);
}
