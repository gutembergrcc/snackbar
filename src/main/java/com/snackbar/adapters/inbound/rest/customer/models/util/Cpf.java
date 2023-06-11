package com.snackbar.adapters.inbound.rest.customer.models.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Cpf {

    String message() default "{invalid.cpfNumber}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}