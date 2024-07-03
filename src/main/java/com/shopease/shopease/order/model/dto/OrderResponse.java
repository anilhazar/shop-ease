package com.shopease.shopease.order.model.dto;

import com.shopease.shopease.order.orderitem.model.dto.OrderItemResponse;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private String id;
    private String customerId;
    private BigDecimal totalPrice;
    private List<OrderItemResponse> items;
}
