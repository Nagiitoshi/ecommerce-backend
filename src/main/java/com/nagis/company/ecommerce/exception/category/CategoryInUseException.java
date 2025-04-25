package com.nagis.company.ecommerce.exception.category;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CategoryInUseException extends BusinessException {
    public CategoryInUseException(Long categoryId, int productCount) {
        super(String.format("Categoria %d vinculada a %d produtos", categoryId, productCount),
                HttpStatus.CONFLICT);
    }
}
