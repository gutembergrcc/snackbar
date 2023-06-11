package com.snackbar.application.core.domain.customer;

import com.snackbar.application.core.domain.validation.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void givenAValidParams_whenCallNewCustomer_thenInstantiateACustomer() {
        final var expectedFirstName = "Jose";
        final var expectedLastName = "Silva";
        final var expectedCPF = "09293493219";

        final var actualCustomer = Customer.newCustomer(expectedFirstName, expectedLastName, expectedCPF);

        Assertions.assertNotNull(actualCustomer);
        Assertions.assertNotNull(actualCustomer.getId());
        Assertions.assertEquals(expectedFirstName, actualCustomer.getFirstName());
        Assertions.assertEquals(expectedLastName, actualCustomer.getLastName());
        Assertions.assertEquals(expectedCPF, actualCustomer.getCpf());
    }

    @Test
    public void givenAnInvalidNullFirstName_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'firstName' should not be null";
        final var expectedLastName = "Silva";
        final var expectedCPF = "09293493219";

        final var actualCustomer = Customer.newCustomer(null, expectedLastName, expectedCPF);

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullLastName_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'lastName' should not be null";
        final var expectedFirstName = "Jose";
        final var expectedCPF = "09293493219";

        final var actualCustomer = Customer.newCustomer(expectedFirstName, null, expectedCPF);

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullCPF_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'CPF' should not be null";
        final var expectedFirstName = "Jose";
        final var expectedLastName = "Silva";

        final var actualCustomer = Customer.newCustomer(expectedFirstName, expectedLastName, null);

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyFirstName_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'firstName' should not be empty";
        final var expectedLastName = "Silva";
        final var expectedCPF = "09293493219";

        final var actualCustomer = Customer.newCustomer(" ", expectedLastName, expectedCPF);

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyLastName_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'lastName' should not be empty";
        final var expectedFirstName = "Jose";
        final var expectedCPF = "09293493219";

        final var actualCustomer = Customer.newCustomer(expectedFirstName, " ", expectedCPF);

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyCPF_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'CPF' should not be empty";
        final var expectedFirstName = "Jose";
        final var expectedLastName = "Silva";

        final var actualCustomer = Customer.newCustomer(expectedFirstName, expectedLastName, " ");

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidFirstName_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'firstName' must be between 3 and 255 characters";
        final var expectedFirstName = "JJ";
        final var expectedLastName = "Silva";
        final var expectedCPF = "09293493219";

        final var actualCustomer = Customer.newCustomer(expectedFirstName, expectedLastName, expectedCPF);

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidLastName_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'lastName' must be between 3 and 255 characters";
        final var expectedFirstName = "Jose";
        final var expectedLastName = "S";
        final var expectedCPF = "09293493219";

        final var actualCustomer = Customer.newCustomer(expectedFirstName, expectedLastName, expectedCPF);

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidFirstNameMore255Chars_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'firstName' must be between 3 and 255 characters";
        final var expectedFirstName = """
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                """;
        final var expectedLastName = "Silva";
        final var expectedCPF = "09293493219";

        final var actualCustomer = Customer.newCustomer(expectedFirstName, expectedLastName, expectedCPF);

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidLastNameMore255Chars_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'lastName' must be between 3 and 255 characters";
        final var expectedLastName = """
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                """;
        final var expectedFirstName = "Jose";
        final var expectedCPF = "09293493219";

        final var actualCustomer = Customer.newCustomer(expectedFirstName, expectedLastName, expectedCPF);

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidCPF_whenCallNewCustomerAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'CPF' should be numeric";
        final var expectedFirstName = "Jose";
        final var expectedLastName = "Silva";

        final var actualCustomer = Customer.newCustomer(expectedFirstName, expectedLastName, "023.123.123.12");

        Notification notification = Notification.create();
        actualCustomer.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }
}
