package com.shopease.shopease.order.service;

import com.shopease.shopease.order.model.dto.OrderRequest;
import com.shopease.shopease.order.model.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    void createOrder(OrderRequest orderRequest);

    void placeOrder(String id);

    OrderResponse getOrderById(String id);

    List<OrderResponse> getOrdersByCustomerId(String customerId);

    void deleteOrder(String id);
}
