package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.SystemProfile;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing.RestClient;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.NotificationSender;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.profile.NotificationProfile;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner.AuthClientNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    @Bean
    public NotificationSender notificationSender(final RestClient restClient,
                                                 final SystemProfile systemProfile) {
        return new AuthClientNotifier(restClient, systemProfile);
    }
}
