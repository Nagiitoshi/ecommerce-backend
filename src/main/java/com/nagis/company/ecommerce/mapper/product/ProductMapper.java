package com.nagis.company.ecommerce.mapper.product;

import com.nagis.company.ecommerce.dto.product.ProductRequestDTO;
import com.nagis.company.ecommerce.dto.product.ProductResponseDTO;
import com.nagis.company.ecommerce.model.product.Product;
import com.nagis.company.ecommerce.model.product.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Convert RequestDTO -> Entity (ignores category for manual handling)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductRequestDTO dto);

    // Convert Entity -> ResponseDTO (includes category IDs/names)
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    ProductResponseDTO toDTO(Product product);

    // Update existing Entity from DTO (ignores ID and category)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateFromDTO(ProductRequestDTO dto, @MappingTarget Product product);

    // Helper method for manual category mapping
    default ProductCategory mapCategoryIdToCategory(Long categoryId) {
        if (categoryId == null) return null;
        ProductCategory category = new ProductCategory();
        category.setId(categoryId);
        return category;
    }
}