package com.snackbar.adapters.inbound.rest.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderResponse (
        @JsonProperty("id") String id,
        @JsonProperty("ticket") String ticket,
        @JsonProperty("status") String status
) {
}
