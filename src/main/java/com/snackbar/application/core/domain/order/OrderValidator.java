package com.snackbar.application.core.domain.order;

import com.snackbar.application.core.domain.validation.Error;
import com.snackbar.application.core.domain.validation.ValidationHandler;
import com.snackbar.application.core.domain.validation.Validator;

public class OrderValidator extends Validator {

    public static final int OBSERVATION_MAX_LENGTH = 255;

    public static final int TICKET_MAX_LENGTH = 50;
    public static final int MIN_LENGTH = 1;

    private final Order order;

    protected OrderValidator(final Order order, final ValidationHandler aHandler) {
        super(aHandler);
        this.order = order;
    }

    @Override
    public void validate() {
        checkTicketNameConstraints(order);
        checkObservationConstraints(order);
        checkItemsOrderConstraints(order);
    }

    private void checkItemsOrderConstraints(Order order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            this.validationHandler().append(new Error("The order is necessary almost one item"));
        }
    }

    private void checkTicketNameConstraints(Order order) {
        if (order.getTicket() == null) {
            this.validationHandler().append(new Error("'ticket' should not be null"));
            return;
        }

        if (order.getTicket().isBlank()) {
            this.validationHandler().append(new Error("'ticket' should not be empty"));
            return;
        }

        final int length = order.getTicket().trim().length();
        if (length > TICKET_MAX_LENGTH || length < MIN_LENGTH) {
            this.validationHandler().append(new Error("'ticket' must be between 1 and 50 characters"));
        }
    }

    private void checkObservationConstraints(Order order) {
        String observation = order.getObservation();
        if (observation != null && !observation.isBlank()) {
            final int length = observation.trim().length();
            if (length > OBSERVATION_MAX_LENGTH || length < MIN_LENGTH) {
                this.validationHandler().append(new Error("'observation' must be between 1 and 255 characters"));
            }
        }
    }
}
