package com.shopease.shopease.cart.controller;

import com.shopease.shopease.cart.model.dto.CartRequest;
import com.shopease.shopease.cart.model.dto.CartResponse;
import com.shopease.shopease.cart.service.CartService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@Validated
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Void> createCart(@RequestBody @Valid final CartRequest cartRequest) {
        cartService.createCart(cartRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CartResponse> getCartByCustomerId(@PathVariable @UUID final String customerId) {
        CartResponse cartResponse = cartService.getCartByCustomerId(customerId);
        return ResponseEntity.ok(cartResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable @UUID final String id) {
        cartService.deleteCart(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/empty")
    public ResponseEntity<CartResponse> emptyCart(@PathVariable @UUID final String id) {
        CartResponse cartResponse = cartService.emptyCart(id);
        return ResponseEntity.ok(cartResponse);
    }

}
