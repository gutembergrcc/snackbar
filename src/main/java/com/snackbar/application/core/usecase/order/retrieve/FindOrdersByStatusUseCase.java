package com.snackbar.application.core.usecase.order.retrieve;

import com.snackbar.application.core.domain.order.OrderStatus;
import com.snackbar.application.core.usecase.order.OrderOutput;
import com.snackbar.application.ports.inbound.order.FindOrdersByStatusUseCasePort;
import com.snackbar.application.ports.outbound.order.FindsOrdersByStatusPort;

import java.util.List;

public class FindOrdersByStatusUseCase implements FindOrdersByStatusUseCasePort {

    private final FindsOrdersByStatusPort findsOrdersByStatusPort;

    public FindOrdersByStatusUseCase(FindsOrdersByStatusPort findsOrdersByStatusPort) {
        this.findsOrdersByStatusPort = findsOrdersByStatusPort;
    }

    @Override
    public List<OrderOutput> execute(OrderStatus status) {
        return this.findsOrdersByStatusPort.findOrdersByStatus(status).stream().map(OrderOutput::from).toList();
    }
}
