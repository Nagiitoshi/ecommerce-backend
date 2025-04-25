package com.nagis.company.ecommerce.exception.order;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class OrderItemNotFoundException extends BusinessException {
    public OrderItemNotFoundException(Long itemId) {
        super("Item de pedido não encontrado com ID: " + itemId, HttpStatus.NOT_FOUND);
    }
}
