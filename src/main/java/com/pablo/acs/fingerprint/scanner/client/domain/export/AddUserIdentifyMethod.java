package com.pablo.acs.fingerprint.scanner.client.domain.export;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.IdentificationMethod;

import java.util.ArrayList;
import java.util.Collection;

public class AddUserIdentifyMethod {

    private Collection<UserIdentificationMethod> identifiers = new ArrayList<>();

    public AddUserIdentifyMethod(final long userId, final byte[] identifier) {
        identifiers.add(new UserIdentificationMethod(userId, identifier));
    }

    public class UserIdentificationMethod {
        private long userId;
        private int identificationMethodId;
        private byte[] identifier;

        private UserIdentificationMethod() { }

        public UserIdentificationMethod(final long userId, final byte[] identifier) {
            this.userId = userId;
            identificationMethodId = IdentificationMethod.FINGERPRINT_SCANNER.getId();
            this.identifier = identifier;
        }

        public long getUserId() {
            return userId;
        }

        public int getIdentificationMethodId() {
            return identificationMethodId;
        }

        public byte[] getIdentifier() {
            return identifier;
        }
    }
}
