package com.nagis.company.ecommerce.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "SKU é obrigatório")
    private String sku;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(max = 255, message = "Nome do produto deve ter no máximo 255 caracteres")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private BigDecimal price;

    @Column(name = "image_url")
    @URL(message = "URL da imagem deve ser válida")
    private String imageUrl;

    @Column(name = "active", nullable = false)
    @Builder.Default
    private Boolean active = true;

    @Column(name = "stock_quantity", nullable = false)
    @Min(value = 0, message = "Quantidade em estoque não pode ser negativa")
    private Integer stockQuantity;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public void updateStock(Integer quantity) {
        this.stockQuantity += quantity;
        if (this.stockQuantity < 0) {
            throw new IllegalStateException("Estoque não pode ficar negativo");
        }
    }
}