package com.pablo.acs.fingerprint.scanner.client.domain.export;

public class AddUserIdentifyMethod {

    private final int userId;
    private final byte[] identifier;
    private final int identificationMethodId;

    public AddUserIdentifyMethod(final int userId, final byte[] identifier) {
        this.userId = userId;
        this.identifier = identifier;
        this.identificationMethodId = 1;
    }

    public int getUserId() {
        return userId;
    }

    public byte[] getIdentifier() {
        return identifier;
    }

    public int getIdentificationMethodId() {
        return identificationMethodId;
    }
}
