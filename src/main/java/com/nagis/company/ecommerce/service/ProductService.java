package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.model.Product;
import com.nagis.company.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Search products by id
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    // Create the product
    @Transactional
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    // Update product
    @Transactional
    public Product updateProduct(Long id, Product product){

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!!!"));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setUnitPrice(product.getUnitPrice());
        existingProduct.setUnitsInStock(product.getUnitsInStock());
        existingProduct.setImageUrl(product.getImageUrl());

        return productRepository.save(existingProduct);
    }

    // Delete product by id
    @Transactional
    public void deleteProductById(Long id){
       productRepository.deleteById(id);
    }
}
