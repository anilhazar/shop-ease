package com.shopease.shopease.order.orderitem.controller;

import com.shopease.shopease.order.orderitem.model.dto.OrderItemRequest;
import com.shopease.shopease.order.orderitem.model.dto.OrderItemResponse;
import com.shopease.shopease.order.orderitem.service.OrderItemService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-item")
@Validated
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrderItem(
            @RequestBody @Valid OrderItemRequest orderItemRequest
    ) {
        orderItemService.createOrderItem(orderItemRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponse> getOrderItemById(@PathVariable @UUID final String id) {
        OrderItemResponse orderItemResponse = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItemResponse);
    }

    @GetMapping("/items")
    public ResponseEntity<List<OrderItemResponse>> getAllOrderItems() {
        List<OrderItemResponse> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItemResponse>> getOrderItemsByOrder(@PathVariable @UUID String orderId) {
        List<OrderItemResponse> orderItems = orderItemService.getOrderItemsByOrder(orderId);
        return ResponseEntity.ok(orderItems);
    }
    

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable @UUID final String id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.ok().build();
    }
}
