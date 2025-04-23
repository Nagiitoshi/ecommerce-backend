package com.nagis.company.ecommerce.controller;

import com.nagis.company.ecommerce.dto.cart.CartItemRequestDTO;
import com.nagis.company.ecommerce.dto.cart.CartResponseDTO;
import com.nagis.company.ecommerce.model.cart.Cart;
import com.nagis.company.ecommerce.repository.CartRepository;
import com.nagis.company.ecommerce.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping
    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/users/{userId}/items")
    public ResponseEntity<CartResponseDTO> addItem(
            @PathVariable Long userId,
            @Valid @RequestBody CartItemRequestDTO itemDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartService.addItemToCart(userId, itemDTO));
    }

    @PatchMapping("/users/{userId}/items/{itemId}")
    public ResponseEntity<CartResponseDTO> updateItem(
            @PathVariable Long userId,
            @PathVariable Long itemId,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateItemQuantity(userId, itemId, quantity));
    }

    @DeleteMapping("/users/{userId}/items/{itemId}")
    public ResponseEntity<Void> removeItem(
            @PathVariable Long userId,
            @PathVariable Long itemId) {
        cartService.removeItemFromCart(userId, itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
