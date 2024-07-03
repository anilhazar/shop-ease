package com.shopease.shopease.order.model.mapper;

import com.shopease.shopease.customer.model.entity.CustomerEntity;
import com.shopease.shopease.order.model.dto.OrderRequest;
import com.shopease.shopease.order.model.dto.OrderResponse;
import com.shopease.shopease.order.model.entity.OrderEntity;
import com.shopease.shopease.order.orderitem.model.mapper.OrderItemMapper;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class OrderMapper {

    public static OrderEntity toEntity(OrderRequest orderRequest) {
        return OrderEntity.builder()
                .customer(CustomerEntity.builder().id(orderRequest.getCustomerId()).build())
                .build();
    }

    public static OrderResponse toResponse(OrderEntity orderEntity) {
        return OrderResponse.builder()
                .id(orderEntity.getId())
                .customerId(orderEntity.getCustomer().getId())
                .totalPrice(orderEntity.getTotalPrice())
                .items(orderEntity.getItems()
                        .stream()
                        .map(OrderItemMapper::toResponse)
                        .toList())
                .build();
    }


    public static List<OrderResponse> toResponse(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
                .map(OrderMapper::toResponse)
                .toList();
    }
}
