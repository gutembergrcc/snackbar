package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.product.Category;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.validation.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderTest {

    private Product getProductMock() {
        final var expectedName = "Big Mac";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;

        return Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);
    }

    private Customer getCustomerMock(){
        return Customer.newCustomer("Jose", "Silva", "09012312312");
    }

    @Test
    public void givenAValidParams_whenCallNewOrder_thenInstantiateAOrder() {
        OrderItem item = OrderItem.newOrderItem(getProductMock(), 2);
        final var order = Order.newOrder("ticket", Arrays.asList(item), getCustomerMock(), null);

        Assertions.assertNotNull(order);
        Assertions.assertNotNull(order.getId());
        Assertions.assertEquals("ticket", order.getTicket());
        Assertions.assertNotNull(order.getCustomer());
        Assertions.assertEquals(item, order.getItems().stream().findFirst().get());
    }

    @Test
    public void givenAnInvalidNullTicket_whenCallNewOrderAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'ticket' should not be null";

        OrderItem item = OrderItem.newOrderItem(getProductMock(), 2);
        final var order = Order.newOrder(null, Arrays.asList(item), getCustomerMock(), "Nada");

        Notification notification = Notification.create();
        order.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyTicket_whenCallNewOrderAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'ticket' should not be empty";

        OrderItem item = OrderItem.newOrderItem(getProductMock(), 2);
        final var order = Order.newOrder("", Arrays.asList(item), getCustomerMock(), "Nada");

        Notification notification = Notification.create();
        order.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidTicket_whenCallNewOrdertAndValidate_thenShouldReceiveError() {
        final String expectedTicket = """
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                """;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'ticket' must be between 1 and 50 characters";

        OrderItem item = OrderItem.newOrderItem(getProductMock(), 2);
        final var order = Order.newOrder(expectedTicket, Arrays.asList(item), getCustomerMock(), "Nada");

        Notification notification = Notification.create();
        order.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidObservation_whenCallNewOrdertAndValidate_thenShouldReceiveError() {
        final String expectedObservation = """
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                """;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'observation' must be between 1 and 255 characters";

        OrderItem item = OrderItem.newOrderItem(getProductMock(), 2);
        final var order = Order.newOrder("A40" , Arrays.asList(item), getCustomerMock(), expectedObservation);

        Notification notification = Notification.create();
        order.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidOrderItems_whenCallNewOrdertAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "The order is necessary almost one item";

        final var order = Order.newOrder("A40" , null, getCustomerMock(), null);

        Notification notification = Notification.create();
        order.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidEmptyOrderItems_whenCallNewOrdertAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "The order is necessary almost one item";

        final var order = Order.newOrder("A40" , new ArrayList<>(), getCustomerMock(), null);

        Notification notification = Notification.create();
        order.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }
}
