package com.nagis.company.ecommerce.dto.order;

import com.nagis.company.ecommerce.model.order.OrderItem;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        Long productId,
        String productName,  // Nome do produto para facilitar no frontend
        String productImage, // URL da imagem principal
        int quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal  // quantity * unitPrice (calculado automaticamente)
) { }
