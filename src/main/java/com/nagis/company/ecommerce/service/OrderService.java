package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.model.Order;
import com.nagis.company.ecommerce.model.ProductCategory;
import com.nagis.company.ecommerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(Long id, Order updateOrder) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found!"));

        existingOrder.setOrderItems(updateOrder.getOrderItems());
        existingOrder.setStatus(updateOrder.getStatus());
        existingOrder.setTotalPrice(updateOrder.getTotalPrice());
        existingOrder.setTotalQuantity(updateOrder.getTotalQuantity());
        existingOrder.setCustomer(updateOrder.getCustomer());
        existingOrder.setBillingAddress(updateOrder.getBillingAddress());
        existingOrder.setShippingAddress(updateOrder.getShippingAddress());

        return orderRepository.save(existingOrder);
    }

    @Transactional
    public void deleteOrderById(Long id){
        orderRepository.deleteById(id);
    }
}
