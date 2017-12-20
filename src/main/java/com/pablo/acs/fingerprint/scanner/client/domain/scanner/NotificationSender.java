package com.pablo.acs.fingerprint.scanner.client.domain.scanner;

public interface NotificationSender {

    void processingFinger();

    void identified(int id);

    void inputFinger(int finger);

    void takeOffFinger();
}
