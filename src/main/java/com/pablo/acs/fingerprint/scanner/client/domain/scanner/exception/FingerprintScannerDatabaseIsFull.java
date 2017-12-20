package com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception;

public class FingerprintScannerDatabaseIsFull extends RuntimeException {

    public FingerprintScannerDatabaseIsFull(final String message) {
        super(message);
    }
}
