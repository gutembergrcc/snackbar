package com.snackbar.application.core.usecase.order.create;

import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.validation.Notification;
import com.snackbar.application.ports.inbound.order.CreateOrderUseCasePort;
import com.snackbar.application.ports.outbound.order.SaveOrderPort;

public class CreateOrderUseCase implements CreateOrderUseCasePort {

    private final SaveOrderPort saveOrderPort;

    public CreateOrderUseCase(SaveOrderPort saveOrderPort) {
        this.saveOrderPort = saveOrderPort;
    }

    @Override
    public Order execute(Order order) {

        final var notification = Notification.create();
        order.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        return this.saveOrderPort.save(order);
    }
}

