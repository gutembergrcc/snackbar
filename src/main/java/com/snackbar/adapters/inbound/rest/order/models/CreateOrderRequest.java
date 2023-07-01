package com.snackbar.adapters.inbound.rest.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record CreateOrderRequest (
        @JsonProperty("ticket") @NotBlank @Schema(description = "Nome da pessoa que identificará o pedido", example = "Manoel") String ticket,
        @JsonProperty("customerId") @NotBlank @Schema(description = "Identificador do cliente", example = "3437524e-1607-11ee-be56-0242ac120002") String customerId,
        @JsonProperty("orderItems") @NotNull @Schema(description = "Itens do pedido") List<OrderItemRequest> items,
        @JsonProperty("observation") @Schema(description = "Observação do pedido", example = "Sem molho") String observation
) {
}
