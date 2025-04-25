package com.nagis.company.ecommerce.exception.payment;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PaymentFailedException extends BusinessException {
    public PaymentFailedException(String gatewayMessage) {
        super("Falha no pagamento: " + gatewayMessage, HttpStatus.PAYMENT_REQUIRED);
    }
}

