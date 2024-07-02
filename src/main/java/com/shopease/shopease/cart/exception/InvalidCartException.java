package com.shopease.shopease.cart.exception;

import java.io.Serial;

public class InvalidCartException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = "Invalid cart!";

    public InvalidCartException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidCartException(String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}