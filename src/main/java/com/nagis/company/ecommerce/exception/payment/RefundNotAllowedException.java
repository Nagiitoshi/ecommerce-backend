package com.nagis.company.ecommerce.exception.payment;

import com.nagis.company.ecommerce.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class RefundNotAllowedException extends BusinessException {
    public RefundNotAllowedException(Long orderId) {
        super("Reembolso não permitido para o pedido: " + orderId, HttpStatus.FORBIDDEN);
    }
}
