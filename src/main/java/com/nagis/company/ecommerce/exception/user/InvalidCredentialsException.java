package com.nagis.company.ecommerce.exception.user;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException() {
        super("E-mail ou senha inválidos", HttpStatus.UNAUTHORIZED);
    }
}
