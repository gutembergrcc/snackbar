package com.snackbar.application.ports.inbound.order;

import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.Status;

import java.util.List;

public interface FindOrdersByStatusUseCasePort {

    List<Order> execute(Status status);
}
