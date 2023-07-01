package com.snackbar.adapters.inbound.rest.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public record OrderItemRequest(
        @JsonProperty("productId") @NotBlank @Schema(description = "identificador do produto", example = "3437524e-1607-11ee-be56-0242ac120002") String productId,
        @JsonProperty("quantity") @NotBlank @Schema(description = "Quantidade do item(ns)", example = "2") Integer quantity
) {
}
