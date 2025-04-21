package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.model.Cart;
import com.nagis.company.ecommerce.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateCartById(Long id, Cart updatedCart) {
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found!"));
        existingCart.setItems(updatedCart.getItems());
        return cartRepository.save(existingCart);
    }

    @Transactional
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }
}