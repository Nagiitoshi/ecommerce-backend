package com.nagis.company.ecommerce.exception.order;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidOrderStatusException extends BusinessException {
    public InvalidOrderStatusException(String status) {
        super("Status de pedido inválido: " + status, HttpStatus.BAD_REQUEST);
    }
}
