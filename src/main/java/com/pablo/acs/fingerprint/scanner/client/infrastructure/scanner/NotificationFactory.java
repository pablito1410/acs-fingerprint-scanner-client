package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

class NotificationFactory {

    static IdentifiedNotification identified(final int id) {
        return new IdentifiedNotification(2, String.valueOf(id));
    }

    static ProcessingFingerNotification processingFinger() {
        return new ProcessingFingerNotification(1);
    }

    static InputFingerNotification inputFinger(final int finger) {
        return new InputFingerNotification(3, finger);
    }

    static Notification takeOffFinger() {
        return new Notification(4);
    }
}
