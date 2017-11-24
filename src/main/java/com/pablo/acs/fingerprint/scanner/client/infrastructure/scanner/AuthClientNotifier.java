package com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner;

import com.pablo.acs.fingerprint.scanner.client.domain.ports.incoming.param.NotificationParams;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing.NotificationSender;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.rest.RestClient;
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

    @Override
    public void processingFinger() {
        final ProcessingFingerNotification notification = NotificationFactory.processingFinger();
        notificationParams.getEndpoints().forEach(endpoint -> restClient.post(endpoint, notification));
        log.info("Notification was send");
    }

    @Override
    public void identified(final int id) {
        final IdentifiedNotification notification = NotificationFactory.identified(id);
        notificationParams.getEndpoints().forEach(endpoint -> restClient.post(endpoint, notification));
        log.info("Notification was send");
    }

}
