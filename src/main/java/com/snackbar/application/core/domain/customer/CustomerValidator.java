package com.snackbar.application.core.domain.customer;

import com.snackbar.application.core.domain.validation.Error;
import com.snackbar.application.core.domain.validation.ValidationHandler;
import com.snackbar.application.core.domain.validation.Validator;

public class CustomerValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    private final Customer customer;

    public CustomerValidator(final Customer customer, final ValidationHandler aHandler) {
        super(aHandler);
        this.customer = customer;
    }

    @Override
    public void validate() {
        String firstName = this.customer.getFirstName();
        checkConstraints("firstName", firstName);
        String lastName = this.customer.getLastName();
        checkConstraints("lastName", lastName);

        String cpfNumber = this.customer.getCpf();
        checkCPF("CPF", cpfNumber);
    }

    private void checkCPF(String fieldName, String value) {
        if (value == null) {
            this.validationHandler().append(new Error("'"+ fieldName +"' should not be null"));
            return;
        }

        if (value.isBlank()) {
            this.validationHandler().append(new Error("'"+ fieldName +"' should not be empty"));
            return;
        }

        boolean isNumeric = value.matches("[0-9]+");
        if (!isNumeric) {
            this.validationHandler().append(new Error("'"+ fieldName +"' should be numeric"));
        }
    }

    private void checkConstraints(String fieldName, String value) {
        if (value == null) {
            this.validationHandler().append(new Error("'"+ fieldName +"' should not be null"));
            return;
        }

        if (value.isBlank()) {
            this.validationHandler().append(new Error("'"+ fieldName +"' should not be empty"));
            return;
        }

        final int length = value.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'"+ fieldName +"' must be between 3 and 255 characters"));
        }
    }
}
