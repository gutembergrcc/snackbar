package com.snackbar.adapters.outbound.persistence.order.repository;

import com.snackbar.application.core.domain.order.Status;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.getName();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        return Stream.of(Status.values()).filter(category -> category.getName().equalsIgnoreCase(value)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
