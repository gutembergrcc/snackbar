package com.snackbar.application.core.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error anError);

    <T> T validate(Validation<T> aValidation);

    List<Error> getErrors();

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error firstError() {
        if (getErrors() != null && !getErrors().isEmpty()) {
            return getErrors().get(0);
        } else {
            return null;
        }
    }

    interface Validation<T> {
        T validate();
    }
}