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
        checkCustomerConstraints();
    }

    private void checkCustomerConstraints() {
        Customer customer = this.order.getCustomer();

        if (customer == null) {
            this.validationHandler().append(new Error("'customer' should not be null"));
        }
    }
}
