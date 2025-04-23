package com.nagis.company.ecommerce.model.cart;

import com.nagis.company.ecommerce.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")  // Nome no plural (convenção SQL)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)  // Melhor nome para FK
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<CartItem> items = new HashSet<>();

    @Column(name = "total_price", precision = 19, scale = 2)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public void addItem(CartItem item) {
        item.setCart(this);
        this.items.add(item);
        calculateTotal();
    }

    public void calculateTotal() {
        this.totalPrice = items.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
