package com.pablo.acs.fingerprint.scanner.client.domain.scanner;

import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.FingerIsNotPressed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.IdentificationFailed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming.FingerprintScannerApi;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FingerprintScannerService {

    private static final Logger log = LoggerFactory.getLogger(FingerprintScannerService.class);
    private final FingerprintScannerApi fingerprintScanner;
    private final NotificationSender notificationSender;

    public FingerprintScannerService(final FingerprintScannerApi fingerprintScanner,
                                     final NotificationSender notificationSender) {
        this.fingerprintScanner = fingerprintScanner;
        this.notificationSender = notificationSender;
    }

    public void onFingerPressed() {
        notifyProcessingFinger();

        try {
            final IdentificationResult result = fingerprintScanner.identify();
            identified(result.id());
        } catch (FingerIsNotPressed e) {
            log.info("Finger is not pressed");
        } catch (IdentificationFailed e) {
            identificationFailed();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isFingerPressed() {
        return fingerprintScanner.fingerIsPressed();
    }

    private void identificationFailed() {
        log.info("Identification failed");
    }

    private void notifyProcessingFinger() {
        log.info("Processing finger...");
        notificationSender.processingFinger();
    }

    private void identified(final int id) {
        log.info("Identified. id={}", id);
        notificationSender.identified(id);
    }

    public void open() {
        final String[] hardwareInfo = fingerprintScanner.open();
        log.info(hardwareInfo[0]);
        log.info(hardwareInfo[1]);
        log.info(hardwareInfo[2]);
    }

    public void setLED(final boolean value) {
        fingerprintScanner.setLED(true);
    }
}
