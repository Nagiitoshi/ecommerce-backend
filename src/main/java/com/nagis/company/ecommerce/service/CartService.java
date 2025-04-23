package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.dto.cart.CartItemRequestDTO;
import com.nagis.company.ecommerce.dto.cart.CartResponseDTO;
import com.nagis.company.ecommerce.mapper.cart.CartItemMapper;
import com.nagis.company.ecommerce.mapper.cart.CartMapper;
import com.nagis.company.ecommerce.model.cart.Cart;
import com.nagis.company.ecommerce.model.cart.CartItem;
import com.nagis.company.ecommerce.model.product.Product;
import com.nagis.company.ecommerce.model.user.User;
import com.nagis.company.ecommerce.repository.CartRepository;
import com.nagis.company.ecommerce.repository.ProductRepository;
import com.nagis.company.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CartResponseDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserIdWithItems(userId)
                .orElseGet(() -> createNewCart(userId));
        return cartMapper.toDTO(cart);
    }

    @Transactional
    public CartResponseDTO addItemToCart(Long userId, CartItemRequestDTO itemDTO) {
        Cart cart = cartRepository.findByUserIdWithItems(userId)
                .orElseGet(() -> createNewCart(userId));

        Product product = productRepository.findById(itemDTO.productId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        validateStock(product, itemDTO.quantity());

        cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(itemDTO.productId()))
                .findFirst()
                .ifPresentOrElse(
                        existingItem -> updateItemQuantity(existingItem, itemDTO.quantity()),
                        () -> addNewItem(cart, itemDTO, product)
                );

        cart.calculateTotal();
        return cartMapper.toDTO(cartRepository.save(cart));
    }

    @Transactional
    public CartResponseDTO updateItemQuantity(Long userId, Long itemId, int newQuantity) {
        Cart cart = cartRepository.findByUserIdWithItems(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item não encontrado no carrinho"));

        validateStock(item.getProduct(), newQuantity);
        item.setQuantity(newQuantity);
        cart.calculateTotal();

        return cartMapper.toDTO(cartRepository.save(cart));
    }

    @Transactional
    public void removeItemFromCart(Long userId, Long itemId) {
        Cart cart = cartRepository.findByUserIdWithItems(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        boolean removed = cart.getItems().removeIf(item -> item.getId().equals(itemId));
        if (removed) {
            cart.calculateTotal();
            cartRepository.save(cart);
        }
    }

    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserIdWithItems(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
        cart.getItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    private Cart createNewCart(Long userId) {
        Cart cart = new Cart();
        cart.setUser(userRepository.getReferenceById(userId)); // Não busca o usuário, só cria referência
        cart.setTotalPrice(BigDecimal.ZERO);
        return cartRepository.save(cart);
    }

    private void addNewItem(Cart cart, CartItemRequestDTO itemDTO, Product product) {
        CartItem newItem = cartItemMapper.toEntity(itemDTO);
        newItem.setProduct(product);
        newItem.setCart(cart);
        cart.getItems().add(newItem);
    }

    private void updateItemQuantity(CartItem item, int additionalQuantity) {
        item.setQuantity(item.getQuantity() + additionalQuantity);
    }

    private void validateStock(Product product, int requestedQuantity) {
        if (product.getStockQuantity() < requestedQuantity) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + product.getName());
        }
    }
}