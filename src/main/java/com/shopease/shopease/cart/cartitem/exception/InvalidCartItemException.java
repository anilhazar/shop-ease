package com.shopease.shopease.cart.cartitem.exception;

import java.io.Serial;

public class InvalidCartItemException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = "Invalid cart item!";

    public InvalidCartItemException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidCartItemException(String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
