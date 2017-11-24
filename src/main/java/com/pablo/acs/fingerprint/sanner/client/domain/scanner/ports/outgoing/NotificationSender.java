package com.pablo.acs.fingerprint.sanner.client.domain.scanner.ports.outgoing;

public interface NotificationSender {

    void processingFinger();

    void identified(int id);
}
