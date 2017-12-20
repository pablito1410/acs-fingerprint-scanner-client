package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ExportService;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.FingerprintScannerService;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.NotificationSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FingerprintScannerTestConfig {

    @Bean
    public FingerprintScannerService fingerprintScannerService(final NotificationSender notificationSender,
                                                               final ExportService exportService) {
        return new FingerprintScannerService(null, notificationSender, exportService);
    }

}