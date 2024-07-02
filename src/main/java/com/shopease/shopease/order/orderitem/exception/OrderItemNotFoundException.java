package com.shopease.shopease.order.orderitem.exception;

import java.io.Serial;

public class OrderItemNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -8138340348762416963L;

    private static final String DEFAULT_MESSAGE = "Order item not found!";

    public OrderItemNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public OrderItemNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
