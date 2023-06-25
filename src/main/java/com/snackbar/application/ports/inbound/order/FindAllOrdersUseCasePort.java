package com.snackbar.application.ports.inbound.order;

import com.snackbar.application.core.domain.order.Order;

import java.util.List;

public interface FindAllOrdersUseCasePort {

    List<Order> execute();
}
