package com.snackbar.application.ports.inbound.order;

import com.snackbar.application.core.usecase.order.OrderOutput;
import com.snackbar.application.core.usecase.order.update.UpdateOrderStatusCommand;

public interface UpdateOrderStatusUseCasePort {

    OrderOutput execute(UpdateOrderStatusCommand command);
}
