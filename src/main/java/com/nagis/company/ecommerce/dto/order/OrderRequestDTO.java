package com.nagis.company.ecommerce.dto.order;

import com.nagis.company.ecommerce.model.user.Address;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record OrderRequestDTO(
        @NotNull(message = "ID do cliente é obrigatório")
        Long customerId,

        @NotNull(message = "Endereço de entrega é obrigatório")
        Address shippingAddress,

        @NotNull(message = "Endereço de cobrança é obrigatório")
        Address billingAddress,

        @NotEmpty(message = "Itens do pedido são obrigatórios")
        Set<OrderItemRequestDTO> items
) {}
