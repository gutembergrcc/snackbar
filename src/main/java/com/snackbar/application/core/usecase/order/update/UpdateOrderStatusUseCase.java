package com.snackbar.application.core.usecase.order.update;

import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.exceptions.ErrorName;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.core.domain.validation.Error;
import com.snackbar.application.core.usecase.order.OrderOutput;
import com.snackbar.application.ports.inbound.order.UpdateOrderStatusUseCasePort;
import com.snackbar.application.ports.outbound.order.FindOrderByIdPort;
import com.snackbar.application.ports.outbound.order.UpdateOrderPort;

import java.util.Optional;

public class UpdateOrderStatusUseCase implements UpdateOrderStatusUseCasePort {

    private final UpdateOrderPort updateOrderPort;

    private final FindOrderByIdPort findOrderByIdPort;

    public UpdateOrderStatusUseCase(UpdateOrderPort updateOrderPort, FindOrderByIdPort findOrderByIdPort) {
        this.updateOrderPort = updateOrderPort;
        this.findOrderByIdPort = findOrderByIdPort;
    }

    @Override
    public OrderOutput execute(UpdateOrderStatusCommand command) {
        Optional<Order> optionalOrder = this.findOrderByIdPort.findOrderById(OrderId.from(command.orderId()));
        if(optionalOrder.isEmpty()){
            throw DomainException.with(new Error(ErrorName.ORDER_NOT_EXIST));
        }

        Order order = optionalOrder.get();
        order.changeStatus(command.status());
        return OrderOutput.from(this.updateOrderPort.update(order));
    }
}
