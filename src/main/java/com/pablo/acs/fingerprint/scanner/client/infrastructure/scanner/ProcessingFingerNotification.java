package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

public class ProcessingFingerNotification extends Notification {

    private ProcessingFingerNotification() {
        super();
    }

    ProcessingFingerNotification(final int notificationType) {
        super(notificationType);
    }
}
