package com.nagis.company.ecommerce.controller;

import com.nagis.company.ecommerce.dto.product.ProductCategoryRequestDTO;
import com.nagis.company.ecommerce.dto.product.ProductCategoryResponseDTO;
import com.nagis.company.ecommerce.service.ProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategoryResponseDTO> create(
            @Valid @RequestBody ProductCategoryRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productCategoryService.findById(id));
    }

    @PostMapping("/{categoryId}/products")
    public ResponseEntity<ProductCategoryResponseDTO> addProducts(
            @PathVariable Long categoryId,
            @RequestBody Set<Long> productIds) {
        return ResponseEntity.ok(productCategoryService.addProducts(categoryId, productIds));
    }
}
