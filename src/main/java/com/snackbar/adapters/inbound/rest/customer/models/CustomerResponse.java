package com.snackbar.adapters.inbound.rest.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackbar.adapters.inbound.rest.customer.models.util.Cpf;
import com.snackbar.application.core.domain.product.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record CustomerResponse(
        @JsonProperty("id") String id,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("cpf") String cpfNumber) {
}
