package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablo.acs.fingerprint.scanner.client.domain.export.ExportService;
import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.SystemProfile;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing.RestClient;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.FingerprintScannerService;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.NotificationSender;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing.FingerprintScannerApi;
import com.pablo.acs.fingerprint.scanner.client.domain.update.UpdateService;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.profile.SystemProfileDefault;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.rest.RestClientDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ContextConfiguration(classes = {
        SystemProfileDefault.class})
public class ExportServiceTestConfig {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    RestClient restClient(final RestTemplate restTemplate, final ObjectMapper objectMapper) {
        return new RestClientDefault(restTemplate, objectMapper);
    }

    @Bean
    public ExportService exportService(final RestClient restClient, final SystemProfile systemProfile) {
        return new ExportService(restClient, systemProfile);
    }

    @Bean
    public FingerprintScannerApi fingerprintScanner() {
        return new FingerprintScannerMock();
    }

    @Bean
    public NotificationSender notificationSender() {
        return new NotificationSenderMock();
    }

    @Bean
    public FingerprintScannerService fingerprintScannerService(final FingerprintScannerApi fingerprintScanner,
                                                               final NotificationSender notificationSender,
                                                               final ExportService exportService) {
        return new FingerprintScannerService(fingerprintScanner,notificationSender, exportService);
    }

    @Bean
    public UpdateService updateService(final RestClient restClient,
                                       final FingerprintScannerService fingerprintScannerService,
                                       final SystemProfile systemProfile) {
        return new UpdateService(restClient, fingerprintScannerService, systemProfile);
    }

}
