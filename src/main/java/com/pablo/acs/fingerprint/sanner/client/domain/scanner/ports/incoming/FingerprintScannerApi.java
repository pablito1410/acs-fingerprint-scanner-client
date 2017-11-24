package com.pablo.acs.fingerprint.sanner.client.domain.scanner.ports.incoming;

import com.pablo.acs.fingerprint.sanner.client.domain.scanner.IdentificationResult;
import com.pablo.acs.fingerprint.sanner.client.domain.scanner.exception.FingerIsNotPressed;
import com.pablo.acs.fingerprint.sanner.client.domain.scanner.exception.IdentificationFailed;

public interface FingerprintScannerApi {
    boolean fingerIsPressed();

    IdentificationResult identify() throws FingerIsNotPressed, IdentificationFailed;

    String[] open();

    void setLED(boolean value);
}
