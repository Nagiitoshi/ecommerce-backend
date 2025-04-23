package com.nagis.company.ecommerce.mapper.cart;

import com.nagis.company.ecommerce.dto.cart.CartRequestDTO;
import com.nagis.company.ecommerce.dto.cart.CartResponseDTO;
import com.nagis.company.ecommerce.model.cart.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", expression = "java(BigDecimal.ZERO)")
    Cart toEntity(CartRequestDTO dto);

    @Mapping(target = "userId", source = "user.id")
    CartResponseDTO toDTO(Cart cart);
}
