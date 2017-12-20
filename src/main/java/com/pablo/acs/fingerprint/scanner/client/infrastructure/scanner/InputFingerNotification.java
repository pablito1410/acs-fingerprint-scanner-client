package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

public class InputFingerNotification extends Notification {

    private int finger;

    private InputFingerNotification() {
        super();
    }

    public InputFingerNotification(final int notificationType, final int finger) {
        super(notificationType);
        this.finger = finger;
    }
}
