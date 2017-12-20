package com.pablo.acs.fingerprint.scanner.client.domain.update.exception;

public class AuthenticationServiceConnectionError extends RuntimeException {

    public AuthenticationServiceConnectionError(final String message) {
        super(message);
    }
}
