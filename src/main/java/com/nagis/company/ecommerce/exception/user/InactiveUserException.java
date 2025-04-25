package com.nagis.company.ecommerce.exception.user;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InactiveUserException extends BusinessException {
    public InactiveUserException(Long userId) {
        super("Usuário inativo: " + userId, HttpStatus.FORBIDDEN);
    }
}