package com.snackbar.adapters.inbound.rest.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackbar.application.core.domain.order.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.util.List;

public record CustomerRequest(@JsonProperty("firstName") @NotBlank @Schema(description = "Primeiro nome do cliente", example = "José") String firstName,
                              @JsonProperty("lastName") @NotBlank @Schema(description = "Sobrenome do cliente", example = "Silva") String lastName,
                              @JsonProperty("cpf") @NotBlank @CPF @Schema(description = "CPF do cliente somente números sem ponto (.) e traço (-)", example = "03727327818") String cpfNumber,
                              Order orderId) {
}

