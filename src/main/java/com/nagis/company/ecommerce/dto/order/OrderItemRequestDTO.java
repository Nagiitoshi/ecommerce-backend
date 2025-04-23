package com.nagis.company.ecommerce.dto.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderItemRequestDTO(
        @NotNull(message = "ID do produto é obrigatório")
        Long productId,

        @Min(value = 1, message = "Quantidade mínima é 1")
        int quantity,

        @DecimalMin(value = "0.01", message = "Preço unitário inválido")
        BigDecimal unitPrice
) {}
