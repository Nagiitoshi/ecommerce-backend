package com.nagis.company.ecommerce.dto.cart;

import java.math.BigDecimal;

public record CartItemResponseDTO(
        Long id,
        Long productId,
        String productName,
        String productImage,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {}
