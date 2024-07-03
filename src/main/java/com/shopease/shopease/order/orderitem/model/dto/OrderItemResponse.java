package com.shopease.shopease.order.orderitem.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {
    private String id;
    private String orderId;
    private String productId;
    private Long quantity;
    private BigDecimal price;

}
