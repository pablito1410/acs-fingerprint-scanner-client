package com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception;

public class FingerprintScannerTimeoutException extends RuntimeException {

    public FingerprintScannerTimeoutException(final String message) {
        super(message);
    }
}
