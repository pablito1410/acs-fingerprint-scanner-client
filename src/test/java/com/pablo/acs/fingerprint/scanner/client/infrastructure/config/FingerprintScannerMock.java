package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.pablo.acs.fingerprint.scanner.client.domain.scanner.IdentificationResult;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.FingerIsNotPressed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.IdentificationFailed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing.FingerprintScannerApi;

import java.util.HashMap;
import java.util.Map;

class FingerprintScannerMock implements FingerprintScannerApi {

    private final Map<Integer, byte[]> templates = new HashMap<>();

    @Override
    public boolean isFingerPressed() {
        return false;
    }

    @Override
    public IdentificationResult identify() throws FingerIsNotPressed, IdentificationFailed {
        return null;
    }

    @Override
    public String[] open() {
        return new String[0];
    }

    @Override
    public void setLED(final boolean value) {

    }

    @Override
    public void enrollStart(final int id) {

    }

    @Override
    public boolean enroll1() {
        return false;
    }

    @Override
    public boolean enroll2() {
        return false;
    }

    @Override
    public boolean enroll3() {
        return false;
    }

    @Override
    public boolean captureFinger() {
        return false;
    }

    @Override
    public int getEnrollCount() {
        return 0;
    }

    @Override
    public boolean checkEnrolled(final int id) {
        return false;
    }

    @Override
    public byte[] getTemplate(final int id) {
        return templates.get(id);
    }

    @Override
    public void setTemplate(final byte[] template, final int id, final boolean duplicateCheck) {
        templates.put(id, template);
    }

    @Override
    public void delete(final int id) {
        templates.remove(id);
    }
}
