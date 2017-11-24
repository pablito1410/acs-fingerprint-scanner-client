package com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing;

public interface NotificationSender {

    void processingFinger();

    void identified(int id);
}
