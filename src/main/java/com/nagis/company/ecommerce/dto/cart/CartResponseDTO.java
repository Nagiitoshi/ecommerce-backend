package com.nagis.company.ecommerce.dto.cart;

import java.math.BigDecimal;

public record CartResponseDTO(
        Long id,
        Long userId,
        BigDecimal totalPrice,
        Set<CartItemResponseDTO> items
) {}
