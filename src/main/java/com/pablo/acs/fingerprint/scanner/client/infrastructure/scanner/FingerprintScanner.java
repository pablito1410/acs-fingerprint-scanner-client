package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

import com.pablo.acs.fingerprint.scanner.client.domain.scanner.IdentificationResult;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.FingerIsNotPressed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.FingerprintScannerException;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.IdAlreadyExistException;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.IdentificationFailed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing.FingerprintScannerApi;
import com.pablo.gt511c1r.Error;
import com.pablo.gt511c1r.GT511C1R;
import com.pablo.gt511c1r.exception.CommandProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class FingerprintScanner implements FingerprintScannerApi {

    private static final Logger log = LoggerFactory.getLogger(FingerprintScanner.class);

    private final GT511C1R device;

    @Autowired
    public FingerprintScanner(final GT511C1R device) {
        this.device = device;
    }

    @Override
    public boolean fingerIsPressed() {
        return device.isPressFinger();
    }

    @Override
    public IdentificationResult identify() throws FingerIsNotPressed, IdentificationFailed {
        if (!fingerIsPressed()) {
            throw new FingerIsNotPressed();
        }

        for (int i = 0; i < 3; i++) {
            final IdentificationResult result = tryIdentify();
            if (result.success()) {
                return result;
            }
        }

        throw new IdentificationFailed();
    }

    private IdentificationResult tryIdentify() {
        try {
            final int id = device.identify();
            return IdentificationResult.success(id);
        } catch (CommandProcessingException e) {
            return IdentificationResult.failed(e.getMessage());
        }
    }

    @Override
    public String[] open() {
        return device.open();
    }

    @Override
    public void setLED(final boolean value) {
        device.setLED(value);
    }

    @Override
    public void enrollStart(final int id) {
        try {
            device.enrollStart(id);
        } catch (CommandProcessingException e) {
            // TODO przerobic commandprocessingexception na konkretne wyjątki w srodku
            if (e.getError().isPresent() && e.getError().get() == Error.NACK_IS_ALREADY_USED) {
                throw new IdAlreadyExistException(e);
            }
            throw new FingerprintScannerException(e);
        }
    }

    @Override
    public boolean enroll1() {
        try {
            return device.enroll1();
        } catch (CommandProcessingException e) {
            throw new FingerprintScannerException(e);
        }
    }

    @Override
    public boolean enroll2() {
        try {
            return device.enroll2();
        } catch (CommandProcessingException e) {
            throw new FingerprintScannerException(e);
        }
    }

    @Override
    public boolean enroll3() {
        try {
            return device.enroll3();
        } catch (CommandProcessingException e) {
            throw new FingerprintScannerException(e);
        }
    }

    @Override
    public boolean captureFinger() {
        try {
            return device.captureFinger(true);
        } catch (CommandProcessingException e) {
            throw new FingerprintScannerException(e);
        }
    }

    @Override
    public int getEnrollCount() {
        return device.getEnrollCount();
    }

    @Override
    public boolean checkEnrolled(final int id) {
        try {
            return device.checkEnrolled(id);
        } catch (CommandProcessingException e) {
            throw new FingerprintScannerException(e);
        }
    }

    @Override
    public byte[] getTemplate(final int id) {
        try {
            return device.getTemplate(id);
        } catch (CommandProcessingException e) {
            throw new FingerprintScannerException(e);
        }
    }

    @Override
    public void setTemplate(byte[] template, int id, boolean duplicateCheck) {
        try {
            device.setTemplate(template, id, duplicateCheck);
        } catch (CommandProcessingException e) {
            throw new FingerprintScannerException(e);
        }
    }

    @Override
    public void delete(final int id) {
        try {
            device.deleteId(id);
        } catch (CommandProcessingException e) {
            throw new FingerprintScannerException(e);
        }
    }
}
