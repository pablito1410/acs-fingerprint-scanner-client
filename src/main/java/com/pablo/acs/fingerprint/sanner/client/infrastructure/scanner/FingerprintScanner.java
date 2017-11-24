package com.pablo.acs.fingerprint.sanner.client.infrastructure.scanner;

import com.pablo.acs.fingerprint.sanner.client.domain.scanner.ports.incoming.FingerprintScannerApi;
import com.pablo.acs.fingerprint.sanner.client.domain.scanner.IdentificationResult;
import com.pablo.acs.fingerprint.sanner.client.domain.scanner.exception.FingerIsNotPressed;
import com.pablo.acs.fingerprint.sanner.client.domain.scanner.exception.IdentificationFailed;
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
}
