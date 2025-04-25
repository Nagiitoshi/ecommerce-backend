package com.nagis.company.ecommerce.exception.user;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(Long userId) {
        super("Usuário não encontrado com ID: " + userId, HttpStatus.NOT_FOUND);
    }
}
