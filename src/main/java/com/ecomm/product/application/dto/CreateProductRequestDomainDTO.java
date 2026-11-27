package com.ecomm.product.application.dto;

import com.ecomm.product.domain.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProductRequestDomainDTO(
        @NotBlank
        String name,
        String description,
        @NotBlank
        String sku,
        @NotNull(message = "Price is required")
        BigDecimal price,
        @NotNull(message = "Categoria is required")
        Categoria categoria) {

}
