package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.pablo.acs.fingerprint.scanner.client.domain.scanner.NotificationSender;

class NotificationSenderMock implements NotificationSender {
    @Override
    public void processingFinger() {

    }

    @Override
    public void identified(final int id) {

    }

    @Override
    public void inputFinger(final int finger) {

    }

    @Override
    public void takeOffFinger() {

    }
}
