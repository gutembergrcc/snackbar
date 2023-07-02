package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.Entity;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.validation.ValidationHandler;

public class OrderItem  extends Entity<OrderItemId> {

    private final Product product;
    private final Integer quantity;

    private OrderItem(final OrderItemId itemId, final Product product, final Integer quantity) {
        super(itemId);
        this.product = product;
        this.quantity = quantity;
    }

    public static OrderItem newOrderItem(final Product product, final Integer quantity) {
        final var id = OrderItemId.unique();
        return new OrderItem(id, product, quantity);
    }

    public static OrderItem with(final OrderItemId orderItemId, final Product product, final Integer quantity) {
        return new OrderItem(orderItemId, product, quantity);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new OrderItemValidator(this, handler).validate();
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }


}
