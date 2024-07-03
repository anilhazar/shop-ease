package com.shopease.shopease.cart.cartitem.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponse {
    private String id;
    private String cartId;
    private String productId;
    private Long quantity;
    private BigDecimal price;

}
