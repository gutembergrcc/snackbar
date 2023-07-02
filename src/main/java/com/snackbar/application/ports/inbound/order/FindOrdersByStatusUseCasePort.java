package com.snackbar.application.ports.inbound.order;

import com.snackbar.application.core.domain.order.OrderStatus;
import com.snackbar.application.core.usecase.order.OrderOutput;

import java.util.List;

public interface FindOrdersByStatusUseCasePort {

    List<OrderOutput> execute(OrderStatus status);
}
