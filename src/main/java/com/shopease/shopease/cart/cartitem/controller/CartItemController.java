package com.shopease.shopease.cart.cartitem.controller;

import com.shopease.shopease.cart.cartitem.model.dto.CartItemChangeQuantityRequest;
import com.shopease.shopease.cart.cartitem.model.dto.CartItemRequest;
import com.shopease.shopease.cart.cartitem.model.dto.CartItemResponse;
import com.shopease.shopease.cart.cartitem.service.CartItemService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-item")
@Validated
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity<Void> addCartItem(@RequestBody @Valid final CartItemRequest cartItemRequest) {
        cartItemService.addCartItem(cartItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemResponse> getCartItemById(@PathVariable @UUID final String id) {
        CartItemResponse cartItemResponse = cartItemService.getCartItemById(id);
        return ResponseEntity.ok(cartItemResponse);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItemResponse>> getCartItemsByCartId(@PathVariable final String cartId) {
        List<CartItemResponse> cartItems = cartItemService.getCartItemsByCartId(cartId);
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/{id}/quantity")
    public ResponseEntity<Void> updateCartItemQuantity(@PathVariable @UUID final String id,
                                                       @RequestBody @Valid final CartItemChangeQuantityRequest
                                                               cartItemChangeQuantityRequest) {
        cartItemService.updateCartItemQuantity(id, cartItemChangeQuantityRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable final String id) {
        cartItemService.removeCartItem(id);
        return ResponseEntity.ok().build();
    }
}
