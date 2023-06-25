package com.snackbar.application.ports.outbound.order;

import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.Status;

import java.util.List;

public interface FindOrdersByStatusPort {

    List<Order> findOrdersByStatus(Status status);
}
