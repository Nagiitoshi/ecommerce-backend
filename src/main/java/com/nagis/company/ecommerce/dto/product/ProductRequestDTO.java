package com.nagis.company.ecommerce.dto.product;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank(message = "SKU é obrigatório")
        String sku,

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
        String name,

        String description,

        @NotNull(message = "Preço é obrigatório")
        @DecimalMin(value = "0.01", message = "Preço deve ser positivo")
        BigDecimal price,

        @URL(message = "URL da imagem deve ser válida")
        String imageUrl,

        @NotNull(message = "Status ativo é obrigatório")
        Boolean active,

        @NotNull(message = "Quantidade em estoque é obrigatória")
        @Min(value = 0, message = "Estoque não pode ser negativo")
        Integer stockQuantity,

        @NotNull(message = "Categoria é obrigatória")
        Long categoryId
) {}
