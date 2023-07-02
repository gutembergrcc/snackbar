package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.validation.Error;
import com.snackbar.application.core.domain.validation.ValidationHandler;
import com.snackbar.application.core.domain.validation.Validator;

public class OrderItemValidator extends Validator {

    private final OrderItem item;

    protected OrderItemValidator(final OrderItem item, final ValidationHandler aHandler) {
        super(aHandler);
        this.item = item;
    }

    @Override
    public void validate() {
        checkQuantityConstraints(item);
    }

    private void checkQuantityConstraints(OrderItem item) {
        Integer quantity = item.getQuantity();
        if (quantity != null && quantity.compareTo(0) < 1) {
            this.validationHandler().append(new Error("'quantity' must be greater than zero"));
        }
    }
}
