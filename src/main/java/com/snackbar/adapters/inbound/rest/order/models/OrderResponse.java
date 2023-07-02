package com.snackbar.adapters.inbound.rest.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrderResponse (
        @JsonProperty("id") String id,
        @JsonProperty("ticket") String ticket,
        @JsonProperty("items") List<OrderItemResponse> items,
        @JsonProperty("observation") String observation,
        @JsonProperty("status") String status
) {
}
