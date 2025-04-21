package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.model.Product;
import com.nagis.company.ecommerce.model.ProductCategory;
import com.nagis.company.ecommerce.repository.ProductCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public Optional<ProductCategory> getCategoryById(Long id){
        return productCategoryRepository.findById(id);
    }

    @Transactional
    public ProductCategory createCategory(ProductCategory category){
        return productCategoryRepository.save(category);
    }

    @Transactional
    public ProductCategory updateCategory(Long id, ProductCategory updateCategory){
        ProductCategory existingProductCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Category not found!!!"));

        existingProductCategory.setCategoryName(updateCategory.getCategoryName());
        return productCategoryRepository.save(existingProductCategory);
    }

    @Transactional
    public void deleteCategoryById(Long id){
        productCategoryRepository.deleteById(id);
    }
}
