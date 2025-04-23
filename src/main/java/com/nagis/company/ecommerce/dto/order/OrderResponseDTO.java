package com.nagis.company.ecommerce.dto.order;

import com.nagis.company.ecommerce.model.order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponseDTO(
        Long id,
        String orderTrackingNumber,
        int totalQuantity,
        BigDecimal totalPrice,
        OrderStatus status,
        LocalDateTime dateCreated,
        LocalDateTime lastUpdated,
        Long customerId,
        AddressResponseDTO shippingAddress,
        AddressResponseDTO billingAddress,
        Set<OrderItemResponseDTO> items
) {}
