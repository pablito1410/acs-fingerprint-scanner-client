package com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming;

public enum IdentificationMethod {
    FINGERPRINT_SCANNER(1),

    ;

    private final int id;

    IdentificationMethod(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
