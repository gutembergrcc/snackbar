package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.product.Category;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.validation.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OrderItemTest {

    private Product getProductMock() {
        final var expectedName = "Big Mac";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;

        return Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);
    }

    @Test
    public void givenAValidParams_whenCallNewOrderItem_thenInstantiateAOrder() {
        int quantity = 2;
        OrderItem item = OrderItem.newOrderItem(getProductMock(), quantity);

        Assertions.assertNotNull(item);
        Assertions.assertNotNull(item.getId());
        Assertions.assertEquals(quantity, item.getQuantity());
    }

    @Test
    public void givenAInvalidParams_whenCallNewOrderItem_thenInstantiateAOrder() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'quantity' must be greater than zero";

        OrderItem item = OrderItem.newOrderItem(getProductMock(), 0);

        Notification notification = Notification.create();
        item.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidQuantityParams_whenCallNewOrderItem_thenInstantiateAOrder() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'quantity' must be greater than zero";

        OrderItem item = OrderItem.newOrderItem(getProductMock(), -1);

        Notification notification = Notification.create();
        item.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }


}
