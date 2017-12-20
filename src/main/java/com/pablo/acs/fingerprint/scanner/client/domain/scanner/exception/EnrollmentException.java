package com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception;

public class EnrollmentException extends RuntimeException {
    public EnrollmentException(final Exception e) {
        super(e);
    }
}
