package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.validation.Error;
import com.snackbar.application.core.domain.validation.ValidationHandler;
import com.snackbar.application.core.domain.validation.Validator;

public class OrderValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    private final Order order;

    public OrderValidator(final Order order, final ValidationHandler aHandler) {
        super(aHandler);
        this.order = order;
    }

    @Override
    public void validate() {
        checkDescriptionConstraints();
        checkCustomerConstraints();
    }

    private void checkDescriptionConstraints() {
        String description = this.order.getDescription();
        checkConstraints("description", description);
        final int length = description.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'"+ description +"' must be between 3 and 255 characters"));
        }
    }

    private void checkCustomerConstraints() {
        Customer customer = this.order.getCustomer();

        if (customer == null) {
            this.validationHandler().append(new Error("'customer' should not be null"));
        }
    }

    private void checkConstraints(String description, String value) {
        if (value == null) {
            this.validationHandler().append(new Error("'"+ description +"' should not be null"));
            return;
        }

        if (value.isBlank()) {
            this.validationHandler().append(new Error("'"+ description +"' should not be empty"));
            return;
        }

        final int length = value.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'"+ description +"' must be between 3 and 255 characters"));
        }
    }
}
