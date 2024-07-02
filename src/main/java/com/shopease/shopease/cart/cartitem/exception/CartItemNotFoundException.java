package com.shopease.shopease.cart.cartitem.exception;

import java.io.Serial;

public class CartItemNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = "Cart item not found!";

    public CartItemNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CartItemNotFoundException(String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
