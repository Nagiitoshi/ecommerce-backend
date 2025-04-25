package com.nagis.company.ecommerce.exception.cart;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class EmptyCartException extends BusinessException {
    public EmptyCartException(Long cartId) {
        super("Carrinho vazio (ID: " + cartId + ")", HttpStatus.BAD_REQUEST);
    }
}