package com.nagis.company.ecommerce.exception.cart;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidCartItemException extends BusinessException {
    public InvalidCartItemException(String message) {
        super("Item inválido: " + message, HttpStatus.BAD_REQUEST);
    }
}
