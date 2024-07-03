package com.shopease.shopease.cart.model.dto;

import com.shopease.shopease.cart.cartitem.model.dto.CartItemResponse;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private String id;
    private String customerId;
    private BigDecimal totalPrice;
    private List<CartItemResponse> items;
}
