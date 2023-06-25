package com.snackbar.application.ports.inbound.order;

import com.snackbar.application.core.domain.order.Order;

public interface UpdateOrderUseCasePort {

    Order execute(Order order);
}
