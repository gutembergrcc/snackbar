package com.snackbar.application.core.domain.customer;

import com.snackbar.application.core.domain.Identifier;
import com.snackbar.application.core.domain.utils.IdUtils;

import java.util.Objects;

public class CustomerId extends Identifier {

    private final String value;

    private CustomerId(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CustomerId from(final String id) {
        return new CustomerId(id);
    }

    public static CustomerId unique() {
        return CustomerId.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return value;
    }
}