package com.snackbar.application.ports.outbound.order;

import com.snackbar.application.core.domain.order.Order;

public interface UpdateOrderPort {

    Order update(Order order);
}
