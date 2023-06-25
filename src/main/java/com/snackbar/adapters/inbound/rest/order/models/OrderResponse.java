package com.snackbar.adapters.inbound.rest.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackbar.application.core.domain.order.Status;

public record OrderResponse(@JsonProperty("id") String id,
                            @JsonProperty("productId") String product,
                            @JsonProperty("customerId")String customer,
                            @JsonProperty("description") String description,
                            @JsonProperty Status status) {
}
