package com.shopease.shopease.order.orderitem.exception;

import java.io.Serial;

public class InvalidOrderItemException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = "Invalid order item!";

    public InvalidOrderItemException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidOrderItemException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
