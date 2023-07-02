package com.snackbar.application.ports.outbound.order;

import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.OrderStatus;

import java.util.List;

public interface FindsOrdersByStatusPort {

    List<Order> findOrdersByStatus(OrderStatus status);
}
