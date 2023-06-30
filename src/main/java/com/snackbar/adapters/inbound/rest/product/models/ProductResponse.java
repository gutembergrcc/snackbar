package com.snackbar.adapters.inbound.rest.product.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackbar.application.core.domain.product.Category;

import java.math.BigDecimal;

public record ProductResponse(@JsonProperty("id") String id,
                              @JsonProperty("name") String name,
                              @JsonProperty("price") BigDecimal price,
                              @JsonProperty("quantity") Integer quantity,
                              @JsonProperty("description") String description,
                              @JsonProperty Category category) {
}
