package com.snackbar.application.ports.inbound.order;

import com.snackbar.application.core.usecase.order.OrderOutput;

import java.util.List;

public interface FindAllOrdersUseCasePort {

    List<OrderOutput> execute();
}
