package com.pablo.acs.fingerprint.scanner.client.domain.scanner;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ExportService;
import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.ExportFingerprintTemplate;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.EnrollmentException;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.FingerIsNotPressed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.FingerprintScannerTimeoutException;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.IdentificationFailed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming.EnrollCommand;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming.GetTemplate;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming.SetTemplates;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing.FingerprintScannerApi;
import com.pablo.acs.fingerprint.scanner.client.domain.util.BytesUtils;
import com.pablo.gt511c1r.exception.SerialPortIsNotOpenedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class FingerprintScannerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FingerprintScannerService.class);
    private static final int MAX_ENROLL_COUNT = 20;
    private static final long TIMEOUT = 100;
    private final FingerprintScannerApi fingerprintScanner;
    private final NotificationSender notificationSender;
    private static final Object LOCK = new Object();
    private final ExportService exportService;

    public FingerprintScannerService(final FingerprintScannerApi fingerprintScanner,
                                     final NotificationSender notificationSender,
                                     final ExportService exportService) {
        this.fingerprintScanner = fingerprintScanner;
        this.notificationSender = notificationSender;
        this.exportService = exportService;
    }

    public void onFingerPressed() {
        synchronized (LOCK) {
            try {
                notifyProcessingFinger();

                try {
                    final IdentificationResult result = fingerprintScanner.identify();
                    identified(result.id());
                } catch (FingerIsNotPressed e) {
                    LOGGER.info("Finger is not pressed");
                } catch (IdentificationFailed e) {
                    identificationFailed();
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (final SerialPortIsNotOpenedException e) {
                LOGGER.warn("Serial port is not opened. Check module connection.");
            }
        }
    }

    public boolean isFingerPressed() {
        synchronized (LOCK) {
//            try {
                return fingerprintScanner.isFingerPressed();
//            } catch (final SerialPortIsNotOpenedException e) {
//                LOGGER.warn("Serial port is not opened. Check module connection.");
//                return false;
//            }
        }
    }

    private void identificationFailed() {
        LOGGER.info("Identification failed");
    }

    private void notifyProcessingFinger() {
        LOGGER.info("Processing finger...");
        notificationSender.processingFinger();
    }

    private void identified(final int id) {
        LOGGER.info("Identified. id={}", id);
        notificationSender.identified(id);
    }

    public void open() {
        try {
            final String[] hardwareInfo = fingerprintScanner.open();
            LOGGER.info(hardwareInfo[0]);
            LOGGER.info(hardwareInfo[1]);
            LOGGER.info(hardwareInfo[2]);
        } catch (final SerialPortIsNotOpenedException e) {
            LOGGER.warn("Serial port is not opened. Check module connection.");
        }
    }

    public void setLED(final boolean value) {
        try {
            fingerprintScanner.setLED(true);
        } catch (final SerialPortIsNotOpenedException e) {
            LOGGER.warn("Serial port is not opened. Check module connection.");
        }
    }

    public void handle(final EnrollCommand command) {
        synchronized (LOCK) {
            try {
                final int id = command.getUserId();

                LOGGER.info("Enrollment process start. Id=" + command.getUserId());

                fingerprintScanner.enrollStart(id);
                Thread.sleep(TIMEOUT);

                notificationSender.inputFinger(1);
                waitForFinger();
                LOGGER.info("capture=" + fingerprintScanner.captureFinger());
                Thread.sleep(TIMEOUT);

                LOGGER.info("enroll1=" + fingerprintScanner.enroll1());
                Thread.sleep(TIMEOUT);

                LOGGER.info("Enroll first fingerprint finished.");

                notificationSender.takeOffFinger();
                while (fingerprintScanner.isFingerPressed()) {
                }
                notificationSender.inputFinger(2);
                waitForFinger();
                LOGGER.info("capture=" + fingerprintScanner.captureFinger());
                Thread.sleep(TIMEOUT);

                LOGGER.info("enroll2=" + fingerprintScanner.enroll2());
                Thread.sleep(TIMEOUT);

                LOGGER.info("Enroll second fingerprint finished.");

                notificationSender.takeOffFinger();
                while (fingerprintScanner.isFingerPressed()) {
                }
                notificationSender.inputFinger(3);
                waitForFinger();
                LOGGER.info("capture=" + fingerprintScanner.captureFinger());
                Thread.sleep(TIMEOUT);

                LOGGER.info("enroll3=" + fingerprintScanner.enroll3());
                Thread.sleep(TIMEOUT);

                LOGGER.info("Enroll third fingerprint finished.");
                LOGGER.info("Enrollment process finished successfully");

                final byte[] template = fingerprintScanner.getTemplate(id);
                exportService.handle(new ExportFingerprintTemplate(id, template));
            } catch (final SerialPortIsNotOpenedException e) {
                LOGGER.warn("Serial port is not opened. Check module connection.");
            } catch (final Exception e) {
                LOGGER.warn("Unexpected error. Deleting template. Id=" + command.getUserId());
                fingerprintScanner.delete(command.getUserId());
                throw new EnrollmentException(e);
            }
        }
    }

    private void waitForFinger() {
        for (int i = 0; i < 30; i++) {
            try {
                if (fingerprintScanner.isFingerPressed()) {
                    return;
                }
                Thread.sleep(100);
            } catch (final InterruptedException e) {

            }
        }
        throw new FingerprintScannerTimeoutException("Timeout!");
    }

    public String getTemplate(final GetTemplate command) {
            synchronized (LOCK) {
                LOGGER.info("Get template process started. Id={}", command.getId());
                final String template = BytesUtils.bytesToHex(fingerprintScanner.getTemplate(command.getId()));
                LOGGER.info("Get template process finished. Id={}", command.getId());
                return template;
            }
    }

    public void handle(final SetTemplates command) {
        try {
            synchronized (LOCK) {
                final Map<Long, byte[]> templates = command.getTemplates();
                templates.forEach((id, template) -> setTemplate(id.intValue(), template));
            }
        } catch (final SerialPortIsNotOpenedException e) {
            LOGGER.warn("Serial port is not opened. Check module connection.");
        }
    }

    private void setTemplate(final int id, final byte[] template) {
            LOGGER.info("Set template process started. Id={}", id);
//        fingerprintScanner.delete(id); // TODO zrpboci duplicate check false
            fingerprintScanner.setTemplate(template, id, false);
            LOGGER.info("Set template finished successfully. Id={}");
    }
}
