package com.nagis.company.ecommerce.mapper.product;

import com.nagis.company.ecommerce.dto.product.ProductCategoryRequestDTO;
import com.nagis.company.ecommerce.dto.product.ProductCategoryResponseDTO;
import com.nagis.company.ecommerce.model.product.Product;
import com.nagis.company.ecommerce.model.product.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    @Mapping(target = "products", ignore = true)
    ProductCategory toEntity(ProductCategoryRequestDTO dto);

    @Mapping(target = "productIds", source = "products", qualifiedByName = "mapProductsToIds")
    ProductCategoryResponseDTO toDTO(ProductCategory category);

    @Named("mapProductsToIds")
    default Set<Long> mapProductsToIds(Set<Product> products) {
        return products.stream()
                .map(Product::getId)
                .collect(Collectors.toSet());
    }
}