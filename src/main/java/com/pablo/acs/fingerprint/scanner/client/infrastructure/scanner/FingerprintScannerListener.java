package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

import com.pablo.acs.fingerprint.scanner.client.domain.scanner.FingerprintScannerService;
import com.pablo.gt511c1r.exception.SerialPortIsNotOpenedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FingerprintScannerListener {

    private static final Logger log = LoggerFactory.getLogger(FingerprintScannerListener.class);
    private final FingerprintScannerService fingerprintScannerService;


    @Autowired
    public FingerprintScannerListener(final FingerprintScannerService fingerprintScannerService) {
        this.fingerprintScannerService = fingerprintScannerService;
    }

    @Scheduled(fixedDelay = 200)
    public void listen() {
        log.debug("Waiting for fingerprint...");
        try {
            if (fingerprintScannerService.isFingerPressed()) {
                fingerprintScannerService.onFingerPressed();
            }
        } catch (final SerialPortIsNotOpenedException e) {
            fingerprintScannerService.open();
            fingerprintScannerService.setLED(true);
        }
    }
}
