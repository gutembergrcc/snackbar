package com.snackbar.application.core.domain.product;

import com.snackbar.application.core.domain.validation.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProductTest {

    @Test
    public void givenAValidParams_whenCallNewProduct_thenInstantiateAProduct() {
        final var expectedName = "Big Mac";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;

        final var actualProduct = Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);

        Assertions.assertNotNull(actualProduct);
        Assertions.assertNotNull(actualProduct.getId());
        Assertions.assertEquals(expectedName, actualProduct.getName());
        Assertions.assertEquals(expectedPrice, actualProduct.getPrice());
        Assertions.assertEquals(expectedCategory, actualProduct.getCategory());
        Assertions.assertEquals(expectedDescription, actualProduct.getDescription());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewProductAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;

        final var actualProduct = Product.newProduct(null, expectedPrice, expectedDescription, expectedCategory);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnEmptyName_whenCallNewProductAndValidate_thenShouldReceiveError() {
        final String expectedName = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;

        final var actualProduct = Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidName_whenCallNewProductAndValidate_thenShouldReceiveError() {
        final String expectedName = "Te ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;

        final var actualProduct = Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidNameMore255Chars_whenCallNewProductAndValidate_thenShouldReceiveError() {
        final String expectedName = """
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;

        final var actualProduct = Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullDescription_whenCallNewProductAndValidate_thenShouldReceiveError() {
        final var expectedName = "Big Mac";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be null";

        final var actualProduct = Product.newProduct(expectedName, expectedPrice, null, expectedCategory);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyDescription_whenCallNewProductAndValidate_thenShouldReceiveError() {
        final var expectedName = "Big Mac";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be empty";
        final String expectedDescription = " ";


        final var actualProduct = Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidAcronymLess3_whenCallNewproductyAndValidate_thenShouldReceiveError() {
        final var expectedName = "Big Mac";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 3 and 255 characters";
        final String expectedDescription = "D";

        final var actualProduct = Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidAcronymMore30_whenCallNewProductAndValidate_thenShouldReceiveError() {
        final var expectedName = "Big Mac";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 3 and 255 characters";
        final String expectedDescription = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

        final var actualProduct = Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullPrice_whenCallNewProductAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'price' should not be null";
        final var expectedName = "Big Mac";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedCategory = Category.SNACK;

        final var actualProduct = Product.newProduct(expectedName, null, expectedDescription, expectedCategory);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNegativePrice_whenCallNewProductAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'price' should not be negative";
        final var expectedName = "Big Mac";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedPrice = BigDecimal.valueOf(-5.0);
        final var expectedCategory = Category.SNACK;

        final var actualProduct = Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullCategory_whenCallNewProductAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'category' should not be null";
        final var expectedName = "Big Mac";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedPrice = BigDecimal.valueOf(25.0);

        final var actualProduct = Product.newProduct(expectedName, expectedPrice, expectedDescription, null);

        Notification notification = Notification.create();
        actualProduct.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }
}

