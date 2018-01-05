package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ExportService;
import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.SystemProfile;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.NotificationSender;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing.FingerprintScannerApi;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.FingerprintScannerService;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner.FingerprintScannerListener;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.scanner.FingerprintScanner;
import com.pablo.gt511c1r.GT511C1R;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FingerprintScannerConfig {

    private SystemProfile systemProfile;

    @Bean
    public GT511C1R gt511c1r() {
        return new GT511C1R(systemProfile.getSerialPort());
    }

    @Bean
    public FingerprintScannerApi fingerprintScanner(final GT511C1R gt511c1r) {
        return new FingerprintScanner(gt511c1r);
    }

    @Bean
    public FingerprintScannerService fingerprintScannerService(final FingerprintScannerApi fingerprintScanner,
                                                               final NotificationSender notificationSender,
                                                               final ExportService exportService) {
        return new FingerprintScannerService(fingerprintScanner, notificationSender, exportService);
    }

}
