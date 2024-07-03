package com.shopease.shopease.order.orderitem.model.mapper;

import com.shopease.shopease.order.model.entity.OrderEntity;
import com.shopease.shopease.order.orderitem.model.dto.OrderItemRequest;
import com.shopease.shopease.order.orderitem.model.dto.OrderItemResponse;
import com.shopease.shopease.order.orderitem.model.entity.OrderItemEntity;
import com.shopease.shopease.product.model.entity.ProductEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class OrderItemMapper {
    public static OrderItemResponse toResponse(OrderItemEntity orderItemEntity) {
        return OrderItemResponse.builder()
                .id(orderItemEntity.getId())
                .orderId(orderItemEntity.getOrder().getId())
                .productId(orderItemEntity.getProduct().getId())
                .price(orderItemEntity.getPrice())
                .quantity(orderItemEntity.getQuantity())
                .build();
    }

    public static List<OrderItemResponse> toResponse(List<OrderItemEntity> orderItemEntities) {
        return orderItemEntities.stream()
                .map(OrderItemMapper::toResponse)
                .toList();
    }

    public static OrderItemEntity toEntity(OrderItemRequest orderItemRequest) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrder(OrderEntity.builder().id(orderItemRequest.getOrderId()).build());
        orderItemEntity.setProduct(ProductEntity.builder().id(orderItemRequest.getOrderId()).build());
        orderItemEntity.setQuantity(orderItemRequest.getQuantity());
        orderItemEntity.setPrice(orderItemRequest.getPrice());
        return orderItemEntity;
    }

    public static List<OrderItemEntity> toEntity(List<OrderItemRequest> orderItemRequests) {
        return orderItemRequests.stream()
                .map(OrderItemMapper::toEntity)
                .toList();
    }
}

