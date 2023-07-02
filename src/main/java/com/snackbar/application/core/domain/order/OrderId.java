package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.Identifier;
import com.snackbar.application.core.domain.utils.IdUtils;

public class OrderId extends Identifier {

    private final String value;

    public OrderId(String value) {
        this.value = value;
    }

    public static OrderId from(final String id) {
        return new OrderId(id);
    }

    public static OrderId unique() {
        return OrderId.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return value;
    }
}
