package com.nagis.company.ecommerce.exception.order;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(Long orderId) {
        super("Pedido não encontrado com ID: " + orderId, HttpStatus.NOT_FOUND);
    }
}