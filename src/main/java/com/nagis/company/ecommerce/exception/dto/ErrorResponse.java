package com.nagis.company.ecommerce.exception.dto;

import java.util.List;

public record ErrorResponse(
        int status,
        String message,
        long timestamp,
        List<String> details
) {
    // Construtor auxiliar para erros sem detalhes
    public ErrorResponse(int status, String message, long timestamp) {
        this(status, message, timestamp, null);
    }
}