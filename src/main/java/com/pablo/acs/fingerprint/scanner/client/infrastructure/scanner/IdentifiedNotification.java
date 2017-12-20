package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

class IdentifiedNotification extends Notification {

    private String id;

    private IdentifiedNotification() {
        super();
    }

    IdentifiedNotification(final int notificationType, final String id) {
        super(notificationType);
        this.id = id;
    }
}
