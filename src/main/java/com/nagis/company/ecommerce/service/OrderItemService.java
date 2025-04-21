package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.model.OrderItem;
import com.nagis.company.ecommerce.repository.OrderItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Transactional
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public OrderItem updateOrderItem(Long id, OrderItem updatedItem) {
        OrderItem existingItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item not found!"));

        existingItem.setImageUrl(updatedItem.getImageUrl());
        existingItem.setQuantity(updatedItem.getQuantity());
        existingItem.setUnitPrice(updatedItem.getUnitPrice());
        existingItem.setProductId(updatedItem.getProductId());
        return orderItemRepository.save(existingItem);
    }

    @Transactional
    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
