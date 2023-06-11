package com.snackbar.application.core.domain.product;

import com.snackbar.application.core.domain.utils.NumberUtils;
import com.snackbar.application.core.domain.validation.Error;
import com.snackbar.application.core.domain.validation.ValidationHandler;
import com.snackbar.application.core.domain.validation.Validator;

import java.math.BigDecimal;

public class ProductValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    private final Product product;

    public ProductValidator(final Product product, final ValidationHandler aHandler) {
        super(aHandler);
        this.product = product;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkDescriptionConstraints();
        checkPriceConstraints();
        checkCategoryConstraints();
    }

    private void checkNameConstraints() {
        String name = this.product.getName();
        checkConstraints("name", name);
    }

    private void checkDescriptionConstraints() {
        String description = this.product.getDescription();
        checkConstraints("description", description);
    }

    private void checkCategoryConstraints() {
        Category category = this.product.getCategory();

        if (category == null) {
            this.validationHandler().append(new Error("'category' should not be null"));
        }
    }

    private void checkPriceConstraints() {
        BigDecimal price = this.product.getPrice();
        String fieldName = "price";

        if (price == null) {
            this.validationHandler().append(new Error("'"+ fieldName +"' should not be null"));
            return;
        }

        if (NumberUtils.isNegative(price)){
            this.validationHandler().append(new Error("'"+ fieldName +"' should not be negative"));
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
