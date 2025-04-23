package com.nagis.company.ecommerce.mapper.cart;

import com.nagis.company.ecommerce.dto.cart.CartItemRequestDTO;
import com.nagis.company.ecommerce.dto.cart.CartItemResponseDTO;
import com.nagis.company.ecommerce.model.cart.CartItem;
import com.nagis.company.ecommerce.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "productId", qualifiedByName = "mapProductIdToProduct")
    CartItem toEntity(CartItemRequestDTO dto);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productImage", source = "product.imageUrl")
    @Mapping(target = "subtotal", expression = "java(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))")
    CartItemResponseDTO toDTO(CartItem item);

    @Named("mapProductIdToProduct")
    default Product mapProductIdToProduct(Long productId) {
        if (productId == null) return null;
        Product product = new Product();
        product.setId(productId);
        return product;
    }
}