package com.nagis.company.ecommerce.exception.user;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException(String email) {
        super("E-mail já cadastrado: " + email, HttpStatus.CONFLICT);
    }
}
