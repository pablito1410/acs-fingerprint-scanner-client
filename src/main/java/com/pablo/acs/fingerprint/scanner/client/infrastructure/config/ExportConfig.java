package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ExportService;
import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.ExportParams;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExportConfig {

    @Bean
    public ExportService exportService(final RestClient restClient, final ExportParams exportParams) {
        return new ExportService(restClient, exportParams);
    }
}
