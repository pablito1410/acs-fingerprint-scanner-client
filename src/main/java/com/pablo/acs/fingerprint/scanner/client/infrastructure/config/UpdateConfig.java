package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.SystemProfile;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing.RestClient;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.FingerprintScannerService;
import com.pablo.acs.fingerprint.scanner.client.domain.update.UpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateConfig {

    @Bean
    public UpdateService updateService(final RestClient restClient,
                                       final FingerprintScannerService fingerprintScannerService,
                                       final SystemProfile systemProfile) {
        return new UpdateService(restClient, fingerprintScannerService, systemProfile);
    }
}
