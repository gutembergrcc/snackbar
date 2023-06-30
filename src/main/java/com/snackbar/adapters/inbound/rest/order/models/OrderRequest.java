package com.snackbar.adapters.inbound.rest.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.order.Status;
import com.snackbar.application.core.domain.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.util.List;

public record OrderRequest(
        @JsonProperty("customerId") @NotBlank @Schema(description = "Id do Cliente vinculado a ordem", example = "6a67a829-182e-489b-9d19-3acaad1c8d74") Customer customerId,
        Status status,
        @JsonProperty("productId") @Schema(description = "Id do Produto vinculado a ordem", example = "6a67a829-182e-489b-9d19-3acaad1c8d74") Product productId) {
}

