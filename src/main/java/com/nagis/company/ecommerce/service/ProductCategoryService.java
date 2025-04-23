package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.dto.product.ProductCategoryRequestDTO;
import com.nagis.company.ecommerce.dto.product.ProductCategoryResponseDTO;
import com.nagis.company.ecommerce.mapper.product.ProductCategoryMapper;
import com.nagis.company.ecommerce.model.product.Product;
import com.nagis.company.ecommerce.model.product.ProductCategory;
import com.nagis.company.ecommerce.repository.ProductCategoryRepository;
import com.nagis.company.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryMapper mapper;

    @Transactional
    public ProductCategoryResponseDTO create(ProductCategoryRequestDTO dto) {
        if (categoryRepository.existsByCategoryName(dto.categoryName())) {
            throw new RuntimeException("Categoria já existe!");
        }

        ProductCategory category = mapper.toEntity(dto);
        return mapper.toDTO(categoryRepository.save(category));
    }

    public ProductCategoryResponseDTO findById(Long id) {
        ProductCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        return mapper.toDTO(category);
    }

    @Transactional
    public ProductCategoryResponseDTO addProducts(Long categoryId, Set<Long> productIds) {
        ProductCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        Set<Product> products = new HashSet<>(productRepository.findAllById(productIds));
        products.forEach(product -> product.setCategory(category));

        return mapper.toDTO(categoryRepository.save(category));
    }
}
