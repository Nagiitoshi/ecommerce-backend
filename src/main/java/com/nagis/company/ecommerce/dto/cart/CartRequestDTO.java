package com.nagis.company.ecommerce.dto.cart;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CartRequestDTO(
        @NotNull(message = "ID do usuário é obrigatório")
        Long userId,

        @NotEmpty(message = "O carrinho não pode estar vazio")
        Set<CartItemRequestDTO> items
) {}
