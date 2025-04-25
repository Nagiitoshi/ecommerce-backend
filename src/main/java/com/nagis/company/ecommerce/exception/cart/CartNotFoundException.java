package com.nagis.company.ecommerce.exception.cart;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CartNotFoundException extends BusinessException {
    public CartNotFoundException(Long userId) {
        super("Carrinho não encontrado para o usuário: " + userId, HttpStatus.NOT_FOUND);
    }
}