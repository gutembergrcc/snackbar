package com.snackbar.adapters.inbound.rest.product.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackbar.application.core.domain.product.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductRequest(@JsonProperty("name") @NotBlank @Schema(description = "Nome do produto", example = "Hambúrguer") String name,
                             @JsonProperty("price") @NotNull @Schema(description = "Preço do produto", example = "35.99") BigDecimal price,
                             @JsonProperty("description") @NotBlank @Schema(description = "Descrição do produto", example = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.") String description,
                             @JsonProperty ("category") @NotNull @Schema(description = "Categoria do produto, verificar os Enums validos", example = "SNACK") Category category) {
}

