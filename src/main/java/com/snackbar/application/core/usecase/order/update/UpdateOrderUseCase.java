package com.snackbar.application.core.usecase.order.update;

import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.validation.Notification;
import com.snackbar.application.ports.inbound.order.UpdateOrderUseCasePort;
import com.snackbar.application.ports.outbound.order.UpdateOrderPort;

public class UpdateOrderUseCase implements UpdateOrderUseCasePort {

    private final UpdateOrderPort updateOrderPort;

    public UpdateOrderUseCase(UpdateOrderPort updateOrderPort) {
        this.updateOrderPort = updateOrderPort;
    }

    @Override
    public Order execute(Order order) {
        final var notification = Notification.create();
        order.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        return this.updateOrderPort.update(order);
    }
}
