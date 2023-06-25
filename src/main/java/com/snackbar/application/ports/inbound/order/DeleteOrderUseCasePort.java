package com.snackbar.application.ports.inbound.order;

import com.snackbar.application.core.domain.order.OrderId;

public interface DeleteOrderUseCasePort {

    void execute(OrderId orderId);
}
