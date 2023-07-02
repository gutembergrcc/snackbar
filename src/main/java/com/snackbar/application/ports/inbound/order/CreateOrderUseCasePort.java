package com.snackbar.application.ports.inbound.order;

import com.snackbar.application.core.usecase.order.OrderOutput;
import com.snackbar.application.core.usecase.order.create.CreateOrderCommand;

public interface CreateOrderUseCasePort {

    OrderOutput execute(CreateOrderCommand command);
}
