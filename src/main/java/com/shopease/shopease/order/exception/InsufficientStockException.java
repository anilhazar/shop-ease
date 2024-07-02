package com.shopease.shopease.order.exception;

public class InsufficientStockException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Insufficient stock for product!";

    public InsufficientStockException() {
        super(DEFAULT_MESSAGE);
    }

    public InsufficientStockException(String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}