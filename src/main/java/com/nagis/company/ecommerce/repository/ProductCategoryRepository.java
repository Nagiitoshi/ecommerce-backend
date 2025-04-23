package com.nagis.company.ecommerce.repository;

import com.nagis.company.ecommerce.model.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    boolean existsByCategoryName(String categoryName);
}
