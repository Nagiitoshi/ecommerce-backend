package com.nagis.company.ecommerce.exception.product;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(Long productId) {
        super("Produto não encontrado com ID: " + productId, HttpStatus.NOT_FOUND);
    }
}