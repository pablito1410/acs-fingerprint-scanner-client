package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

public class Notification {

    private int notificationType;

    protected Notification() { }

    public Notification(final int notificationType) {
        this.notificationType = notificationType;
    }
}
