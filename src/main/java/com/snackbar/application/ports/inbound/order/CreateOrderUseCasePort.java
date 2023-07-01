package com.snackbar.application.ports.inbound.order;

import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.usecase.order.create.CreateOrderCommand;

public interface CreateOrderUseCasePort {

    Order execute(CreateOrderCommand command);
}
