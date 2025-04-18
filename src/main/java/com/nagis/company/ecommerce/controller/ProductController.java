package com.nagis.company.ecommerce.controller;

import com.nagis.company.ecommerce.model.Product;
import com.nagis.company.ecommerce.repository.ProductRepository;
import com.nagis.company.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    // Search all products
    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    // Search product by id
    @GetMapping("/search/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){

        Optional<Product> product = productService.findById(id);

        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    // Update product by id
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody Product updateProduct){
        try{
            Product product = productService.updateProduct(id, updateProduct);

            return ResponseEntity.ok(product);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete product by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        try {
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
