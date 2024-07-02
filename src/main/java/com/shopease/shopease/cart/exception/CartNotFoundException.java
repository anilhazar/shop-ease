package com.shopease.shopease.cart.exception;

import java.io.Serial;

public class CartNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = "Cart not found!";

    public CartNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CartNotFoundException(String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
