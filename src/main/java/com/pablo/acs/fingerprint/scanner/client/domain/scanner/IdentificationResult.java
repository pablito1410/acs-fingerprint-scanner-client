package com.pablo.acs.fingerprint.scanner.client.domain.scanner;

public class IdentificationResult {


    private final Integer id;
    private final String errorDesc;

    private IdentificationResult(final Integer id, final String errorDesc) {
        this.id = id;
        this.errorDesc = errorDesc;
    }

    public static IdentificationResult success(final int id) {
        return new IdentificationResult(id, null);
    }

    public static IdentificationResult failed(final String errorDesc) {
        return new IdentificationResult(null, errorDesc);
    }

    public boolean success() {
        return id != null && errorDesc == null;
    }

    Integer id() {
        return id;
    }

    String getErrorDesc() {
        return errorDesc;
    }

}
