package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.dto.product.ProductRequestDTO;
import com.nagis.company.ecommerce.dto.product.ProductResponseDTO;
import com.nagis.company.ecommerce.exception.product.InsufficientStockException;
import com.nagis.company.ecommerce.exception.product.ProductInactiveException;
import com.nagis.company.ecommerce.exception.product.ProductNotFoundException;
import com.nagis.company.ecommerce.mapper.product.ProductMapper;
import com.nagis.company.ecommerce.model.product.Product;

import com.nagis.company.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;




@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // Busca por ID (retorna DTO)
    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toDTO(product);
    }

    // Criação com DTO
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    // Atualização com DTO
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        // Atualiza apenas os campos permitidos
        productMapper.updateFromDTO(productDTO, existingProduct);
        Product updatedProduct = productRepository.save(existingProduct);

        return productMapper.toDTO(updatedProduct);
    }

    // Delete mantém igual
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    // Listagem paginada (opcional)
    public Page<ProductResponseDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDTO);
    }
}
