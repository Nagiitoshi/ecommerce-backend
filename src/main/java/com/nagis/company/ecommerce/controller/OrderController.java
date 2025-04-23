package com.nagis.company.ecommerce.controller;

import com.nagis.company.ecommerce.dto.order.OrderItemRequestDTO;
import com.nagis.company.ecommerce.dto.order.OrderRequestDTO;
import com.nagis.company.ecommerce.dto.order.OrderResponseDTO;
import com.nagis.company.ecommerce.model.order.OrderStatus;
import com.nagis.company.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @Valid @RequestBody OrderRequestDTO orderDTO) {
        OrderResponseDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderResponseDTO> addItem(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderItemRequestDTO itemDTO) {
        return ResponseEntity.ok(
                orderService.addItemToOrder(orderId, itemDTO)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<Void> removeItem(
            @PathVariable Long orderId,
            @PathVariable Long itemId) {
        orderService.removeItemFromOrder(orderId, itemId);
        return ResponseEntity.noContent().build();
    }
}
