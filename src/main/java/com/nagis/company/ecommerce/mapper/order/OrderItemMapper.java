package com.nagis.company.ecommerce.mapper.order;

import com.nagis.company.ecommerce.dto.order.OrderItemRequestDTO;
import com.nagis.company.ecommerce.dto.order.OrderItemResponseDTO;
import com.nagis.company.ecommerce.model.order.OrderItem;
import com.nagis.company.ecommerce.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "productId", qualifiedByName = "mapProductIdToProduct")
    OrderItem toEntity(OrderItemRequestDTO dto);

    @Mapping(target = "productId", source = "product.id")
    OrderItemResponseDTO toDTO(OrderItem orderItem);

    @Named("mapProductIdToProduct")
    default Product mapProductIdToProduct(Long productId) {
        if (productId == null) return null;
        Product product = new Product();
        product.setId(productId);
        return product;
    }
}
