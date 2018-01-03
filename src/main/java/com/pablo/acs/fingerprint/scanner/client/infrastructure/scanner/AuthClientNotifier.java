package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.SystemProfile;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing.RestClient;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class AuthClientNotifier implements NotificationSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthClientNotifier.class);
    private final RestClient restClient;
    private final SystemProfile systemProfile;

    public AuthClientNotifier(final RestClient restClient, final SystemProfile systemProfile) {
        this.restClient = Objects.requireNonNull(restClient);
        this.systemProfile = Objects.requireNonNull(systemProfile);
    }

    private void notify(final Notification notification) {
        systemProfile.getNotificationEndpoints().forEach(endpoint -> restClient.post(endpoint, notification));
        LOGGER.info("Notification was send");
    }

    @Override
    public void processingFinger() {
        final ProcessingFingerNotification notification = NotificationFactory.processingFinger();
        notify(notification);
    }

    @Override
    public void identified(final int id) {
        final IdentifiedNotification notification = NotificationFactory.identified(id);
        notify(notification);
    }

    @Override
    public void inputFinger(final int finger) {
        final InputFingerNotification notification = NotificationFactory.inputFinger(finger);
        notify(notification);
    }

    @Override
    public void takeOffFinger() {
        final Notification notification = NotificationFactory.takeOffFinger();
        notify(notification);
    }

}
