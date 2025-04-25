package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.dto.order.OrderItemRequestDTO;
import com.nagis.company.ecommerce.dto.order.OrderRequestDTO;
import com.nagis.company.ecommerce.dto.order.OrderResponseDTO;
import com.nagis.company.ecommerce.exception.order.OrderNotFoundException;
import com.nagis.company.ecommerce.mapper.order.OrderItemMapper;
import com.nagis.company.ecommerce.mapper.order.OrderMapper;
import com.nagis.company.ecommerce.model.order.Order;

import com.nagis.company.ecommerce.model.order.OrderItem;
import com.nagis.company.ecommerce.model.order.OrderStatus;
import com.nagis.company.ecommerce.repository.OrderRepository;
import com.nagis.company.ecommerce.repository.ProductRepository;
import com.nagis.company.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        return orderMapper.toDTO(order);
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderDTO) {
        // Client validation
        if (!userRepository.existsById(orderDTO.customerId())) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Order order = orderMapper.toEntity(orderDTO);

        // Generates unique tracking number
        order.setOrderTrackingNumber(generateTrackingNumber());

        calculateOrderTotals(order);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    private String generateTrackingNumber() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private void calculateOrderTotals(Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        int totalQuantity = 0;

        order.setTotalPrice(totalPrice);
        order.setTotalQuantity(totalQuantity);
    }


    @Transactional
    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.setStatus(OrderStatus.valueOf(newStatus.name())); // se status for String no model
        orderRepository.save(order);

        return orderMapper.toDTO(order);
    }

    // Adiciona item a um pedido existente
    @Transactional
    public OrderResponseDTO addItemToOrder(Long orderId, OrderItemRequestDTO itemDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        OrderItem item = orderItemMapper.toEntity(itemDTO);
        order.addItem(item);

        calculateOrderTotals(order);
        return orderMapper.toDTO(orderRepository.save(order));
    }

    // Remove item do pedido
    @Transactional
    public OrderResponseDTO removeItemFromOrder(Long orderId, Long itemId) {
        Order order = orderRepository.findByIdWithItems(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.removeItem(itemId);
        return orderMapper.toDTO(orderRepository.save(order));
    }

}
