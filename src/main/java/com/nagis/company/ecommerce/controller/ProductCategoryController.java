package com.nagis.company.ecommerce.controller;

import com.nagis.company.ecommerce.model.ProductCategory;
import com.nagis.company.ecommerce.repository.ProductCategoryRepository;
import com.nagis.company.ecommerce.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    // Search All Category
    @GetMapping
    public List<ProductCategory> getAllCategories(){
        return productCategoryRepository.findAll();
    }

    // Search Category by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getCategoryById(@PathVariable Long id){
        return productCategoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Category
    @PostMapping
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory category){
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryService.createCategory(category));
    }

    // Update Category by id
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateProductCategoryById(@PathVariable Long id,@RequestBody ProductCategory category){
        return ResponseEntity.ok(productCategoryService.updateCategory(id, category));
    }

    // Delete Category by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        productCategoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
