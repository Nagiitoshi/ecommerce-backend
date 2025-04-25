package com.nagis.company.ecommerce.exception.category;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException(Long categoryId) {
        super("Categoria não encontrada com ID: " + categoryId, HttpStatus.NOT_FOUND);
    }
}
