package com.shopease.shopease.order.orderitem.service;

import com.shopease.shopease.order.orderitem.model.dto.OrderItemRequest;
import com.shopease.shopease.order.orderitem.model.dto.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    void createOrderItem(OrderItemRequest orderItemRequest);

    List<OrderItemResponse> getAllOrderItems();

    List<OrderItemResponse> getOrderItemsByOrder(String orderId);

    OrderItemResponse getOrderItemById(String id);

    void deleteOrderItem(String id);
}
