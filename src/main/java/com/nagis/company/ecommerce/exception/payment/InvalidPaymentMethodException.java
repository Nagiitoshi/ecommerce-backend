package com.nagis.company.ecommerce.exception.payment;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidPaymentMethodException extends BusinessException {
    public InvalidPaymentMethodException(String method) {
        super("Método de pagamento inválido: " + method, HttpStatus.BAD_REQUEST);
    }
}
