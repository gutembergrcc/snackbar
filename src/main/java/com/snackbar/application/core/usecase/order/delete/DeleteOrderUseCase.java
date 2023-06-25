package com.snackbar.application.core.usecase.order.delete;

import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.ports.inbound.order.DeleteOrderUseCasePort;
import com.snackbar.application.ports.outbound.order.DeleteOrderPort;


public class DeleteOrderUseCase implements DeleteOrderUseCasePort {

    private final DeleteOrderPort deleteOrderPort;

    public DeleteOrderUseCase(DeleteOrderPort deleteOrderPort) {
        this.deleteOrderPort = deleteOrderPort;
    }

    @Override
    public void execute(OrderId orderId) {
        this.deleteOrderPort.deleteById(orderId);
    }
}
