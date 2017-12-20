package com.pablo.acs.fingerprint.scanner.client.domain.update.ports.outgoing;

public class FingerprintTemplate {

    private Long userId;
    private byte[] identifier;

    private FingerprintTemplate() { }

    public Long getUserId() {
        return userId;
    }

    public byte[] getIdentifier() {
        return identifier;
    }
}
