package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

class NotificationFactory {

    static IdentifiedNotification identified(final int id) {
        return new IdentifiedNotification(id);
    }

    static ProcessingFingerNotification processingFinger() {
        return new ProcessingFingerNotification();
    }
}
