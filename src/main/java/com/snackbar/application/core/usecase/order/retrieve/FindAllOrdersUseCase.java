package com.snackbar.application.core.usecase.order.retrieve;

import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.usecase.order.OrderOutput;
import com.snackbar.application.ports.inbound.order.FindAllOrdersUseCasePort;
import com.snackbar.application.ports.outbound.order.FindAllOrdersPort;

import java.util.List;

public class FindAllOrdersUseCase implements FindAllOrdersUseCasePort {

    private final FindAllOrdersPort findAllOrdersPort;

    public FindAllOrdersUseCase(FindAllOrdersPort findAllOrdersPort) {
        this.findAllOrdersPort = findAllOrdersPort;
    }

    @Override
    public List<OrderOutput> execute() {
        return this.findAllOrdersPort.findAllOrders().stream().map(OrderOutput::from).toList();
    }
}
