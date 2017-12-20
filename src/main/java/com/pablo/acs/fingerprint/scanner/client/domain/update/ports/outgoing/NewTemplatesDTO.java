package com.pablo.acs.fingerprint.scanner.client.domain.update.ports.outgoing;

import java.util.Collection;

public class NewTemplatesDTO {

    private Collection<FingerprintTemplate> identifiers;

    private NewTemplatesDTO() { }

    public Collection<FingerprintTemplate> getIdentifiers() {
        return identifiers;
    }
}
