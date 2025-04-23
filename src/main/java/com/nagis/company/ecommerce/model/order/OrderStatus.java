package com.nagis.company.ecommerce.model.order;

public enum OrderStatus {
    PENDING,        // When the order is created
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    REFUNDED
}