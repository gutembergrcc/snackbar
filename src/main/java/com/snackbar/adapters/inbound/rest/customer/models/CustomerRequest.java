package com.snackbar.adapters.inbound.rest.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackbar.adapters.inbound.rest.customer.models.util.Cpf;
import com.snackbar.application.core.domain.product.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CustomerRequest(@JsonProperty("firstName") @NotBlank @Schema(description = "Primeiro nome do cliente", example = "José") String firstName,
                              @JsonProperty("lastName") @NotBlank @Schema(description = "Sobrenome do cliente", example = "Silva") String lastName,
                              @JsonProperty("cpf") @NotBlank @Cpf @Schema(description = "CPF do cliente somente números sem ponto (.) e traço (-)", example = "03727327818") String cpfNumber) {
}

