package com.nagis.company.ecommerce.exception.product;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductInactiveException extends BusinessException {
    public ProductInactiveException(Long productId) {
        super("Produto inativo com ID: " + productId, HttpStatus.BAD_REQUEST);
    }
}
