package com.shopease.shopease.authentication.exception;

import java.io.Serial;

public class AuthenticationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = """
            Authentication failed!
            """;

    public AuthenticationException() {
        super(DEFAULT_MESSAGE);
    }

    public AuthenticationException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
