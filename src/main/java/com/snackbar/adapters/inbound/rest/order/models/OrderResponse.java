package com.snackbar.adapters.inbound.rest.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.order.Status;
import com.snackbar.application.core.domain.product.Product;

import java.util.List;

public record OrderResponse(@JsonProperty("id") String id,
                            @JsonProperty("customerId") Customer customer,
                            @JsonProperty Status status,
                            @JsonProperty("products") List<Product> product) {
}
