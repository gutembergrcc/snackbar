package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.Identifier;
import com.snackbar.application.core.domain.utils.IdUtils;

import java.util.Objects;

public class OrderId extends Identifier {

    private final String value;

    private OrderId(String value) {
        this.value = Objects.requireNonNull(value);;
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
