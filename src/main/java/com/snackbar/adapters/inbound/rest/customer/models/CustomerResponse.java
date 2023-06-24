package com.snackbar.adapters.inbound.rest.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        @JsonProperty("id") String id,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("cpf") String cpfNumber) {
}
