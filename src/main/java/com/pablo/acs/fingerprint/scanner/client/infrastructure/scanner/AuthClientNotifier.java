package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

import com.pablo.acs.fingerprint.scanner.client.domain.ports.incoming.param.NotificationParams;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing.RestClient;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class AuthClientNotifier implements NotificationSender {

    private static final Logger log = LoggerFactory.getLogger(AuthClientNotifier.class);
    private final RestClient restClient;
    private final NotificationParams notificationParams;

    public AuthClientNotifier(final RestClient restClient, final NotificationParams notificationParams) {
        this.restClient = Objects.requireNonNull(restClient);
        this.notificationParams = Objects.requireNonNull(notificationParams);
    }

    private void notify(final Notification notification) {
        notificationParams.getEndpoints().forEach(endpoint -> restClient.post(endpoint, notification));
        log.info("Notification was send");
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
