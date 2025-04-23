package com.nagis.company.ecommerce.dto.product;

import java.util.Set;

public record ProductCategoryResponseDTO(
        Long id,
        String categoryName,
        Set<Long> productIds
){}
