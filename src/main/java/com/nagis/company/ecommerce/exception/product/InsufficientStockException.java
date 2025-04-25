package com.nagis.company.ecommerce.exception.product;

import com.nagis.company.ecommerce.exception.BusinessException;

import org.springframework.http.HttpStatus;

public class InsufficientStockException extends BusinessException {
    public InsufficientStockException(String productName) {
        super("Estoque insuficiente para o produto: " + productName, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}