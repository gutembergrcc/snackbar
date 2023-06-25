package com.snackbar.application.ports.outbound.order;

import com.snackbar.application.core.domain.order.OrderId;

public interface DeleteOrderPort {

    void deleteById(OrderId orderId);
}
