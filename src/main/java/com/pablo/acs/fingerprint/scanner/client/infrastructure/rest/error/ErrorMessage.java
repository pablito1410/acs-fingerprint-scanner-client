package com.pablo.acs.fingerprint.scanner.client.infrastructure.rest.error;

class ErrorMessage {

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
