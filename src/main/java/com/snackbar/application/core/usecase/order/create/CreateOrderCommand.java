package com.snackbar.application.core.usecase.order.create;

import java.util.List;

public record CreateOrderCommand(String ticket,
                                 List<CreateOrderItemCommand> items,
                                 String customerId,
                                 String observation) {

    public static CreateOrderCommand with(
            final String ticket,
            final List<CreateOrderItemCommand> items,
            final String customerId,
            final String observation
    ) {
        return new CreateOrderCommand(ticket, items, customerId, observation);
    }
}
