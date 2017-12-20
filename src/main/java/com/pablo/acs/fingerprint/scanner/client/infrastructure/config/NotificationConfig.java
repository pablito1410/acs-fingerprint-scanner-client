package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.pablo.acs.fingerprint.scanner.client.domain.ports.incoming.param.NotificationParams;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing.RestClient;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.NotificationSender;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.profile.NotificationProfile;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner.AuthClientNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    @Bean
    public NotificationParams notificationParams() {
        return new NotificationProfile();
    }

    @Bean
    public NotificationSender notificationSender(final RestClient restClient,
                                                 final NotificationParams notificationParams) {
        return new AuthClientNotifier(restClient, notificationParams);
    }
}
