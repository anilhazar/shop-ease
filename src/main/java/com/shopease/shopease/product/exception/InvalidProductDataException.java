package com.shopease.shopease.product.exception;

import java.io.Serial;

public class InvalidProductDataException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -8138340348762416963L;

    private static final String DEFAULT_MESSAGE = """
            Invalid product data!
            """;

    public InvalidProductDataException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidProductDataException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
