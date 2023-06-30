package com.snackbar.application.core.domain.product;

import com.snackbar.application.core.domain.Entity;
import com.snackbar.application.core.domain.validation.ValidationHandler;

import java.math.BigDecimal;

public class Product extends Entity<ProductId> {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String description;
    private Category category;

    private Product(final ProductId id, final String name, final BigDecimal price, final Integer quantity, final String description, final Category category) {
        super(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
    }

    public static Product newProduct(final String name, final BigDecimal price, final Integer quantity, final String description, final Category category) {
        final var id = ProductId.unique();
        return new Product(id, name, price, quantity, description, category);
    }

    public static Product with(final ProductId productId, final String name, final BigDecimal price, final Integer quantity, final String description, final Category category) {
        return new Product(productId, name, price, quantity, description, category);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ProductValidator(this, handler).validate();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity(){return quantity;}

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }
}
