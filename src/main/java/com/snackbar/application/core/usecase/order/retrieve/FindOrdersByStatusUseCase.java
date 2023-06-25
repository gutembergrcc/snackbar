package com.snackbar.application.core.usecase.order.retrieve;

import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.Status;
import com.snackbar.application.ports.inbound.order.FindOrdersByStatusUseCasePort;
import com.snackbar.application.ports.outbound.order.FindOrdersByStatusPort;

import java.util.List;

public class FindOrdersByStatusUseCase implements FindOrdersByStatusUseCasePort {

    private final FindOrdersByStatusPort findOrdersByStatusPort;

    public FindOrdersByStatusUseCase(FindOrdersByStatusPort findOrdersByStatusPort) {
        this.findOrdersByStatusPort = findOrdersByStatusPort;
    }

    @Override
    public List<Order> execute(Status status) {
        return this.findOrdersByStatusPort.findOrdersByStatus(status);
    }
}
