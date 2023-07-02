package com.snackbar.adapters.inbound.rest.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderItemResponse(@JsonProperty("id") String id,
                                @JsonProperty("productId") String productId,
                                @JsonProperty("productName") String productName,
                                @JsonProperty("quantity") Integer quantity){
}
