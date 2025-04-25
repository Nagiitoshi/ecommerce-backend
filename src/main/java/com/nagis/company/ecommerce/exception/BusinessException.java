package com.nagis.company.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{

    private final HttpStatus statusCode;

    public BusinessException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }
}
