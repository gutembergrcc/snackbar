package com.snackbar.adapters.outbound.persistence.product.repository;

import com.snackbar.application.core.domain.product.Category;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class CategoryConverter implements AttributeConverter<Category, String> {
    @Override
    public String convertToDatabaseColumn(Category category) {
        return category.getName();
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        return Stream.of(Category.values()).filter(category -> category.getName().equalsIgnoreCase(value)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
