package com.pablo.acs.fingerprint.scanner.client.infrastructure.profile;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.ExportParams;
import com.pablo.acs.fingerprint.scanner.client.domain.update.ports.incoming.UpdateParams;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "authentication.service")
public class AuthenticationServiceProfile implements ExportParams, UpdateParams {

    private String exportUrl;

    private String updateUrl;

    @Override
    public String exportUrl() {
        return "http://localhost:7000/identification-methods";
    }

    @Override
    public String updateUrl() {
        return "http://localhost:7000/update?identificationMethod=fingerprint_scanner&lastUpdate=%s";
    }

}
