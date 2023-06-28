package com.snackbar.adapters.inbound.rest.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.order.Status;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public record OrderRequest(
        @JsonProperty("customerId") @NotBlank @Schema(description = "Id do Cliente vinculado a ordem", example = "6a67a829-182e-489b-9d19-3acaad1c8d74") Customer customerId,
        @JsonProperty("description") @Schema(description = "Descrição da ordem", example = "Ordem do Berg") String description,
        Status status) {
}

