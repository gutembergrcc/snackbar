package com.snackbar.application.core.usecase.customer.create;

public record CreateCustomerCommand(String firstName,
                                    String lastName,
                                    String cpf) {

    public static CreateCustomerCommand with(
            final String firstName,
            final String lastName,
            final String cpf
    ) {
        return new CreateCustomerCommand(firstName, lastName, cpf);
    }
}
