package com.snackbar.application.ports.outbound.order;

import com.snackbar.application.core.domain.order.Order;

import java.util.List;

public interface FindAllOrdersPort {

    List<Order> findAllOrders();
}
