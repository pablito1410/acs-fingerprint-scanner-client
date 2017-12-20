package com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing;

import com.pablo.acs.fingerprint.scanner.client.domain.scanner.IdentificationResult;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.FingerIsNotPressed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.IdentificationFailed;

public interface FingerprintScannerApi {

    boolean fingerIsPressed();

    IdentificationResult identify() throws FingerIsNotPressed, IdentificationFailed;

    String[] open();

    void setLED(boolean value);

    void enrollStart(int id);

    boolean enroll1();

    boolean enroll2();

    boolean enroll3();

    boolean captureFinger();

    int getEnrollCount();

    boolean checkEnrolled(int id);

    byte[] getTemplate(int id);

    void setTemplate(byte[] template, int id, boolean duplicateCheck);

    void delete(int id);
}
