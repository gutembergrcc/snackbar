package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.Identifier;
import com.snackbar.application.core.domain.utils.IdUtils;

public class OrderItemId extends Identifier {

    private final String value;

    public OrderItemId(String value) {
        this.value = value;
    }

    public static OrderItemId from(final String id) {
        return new OrderItemId(id);
    }

    public static OrderItemId unique() {
        return OrderItemId.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return value;
    }
}
