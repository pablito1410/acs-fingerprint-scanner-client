package com.pablo.acs.fingerprint.scanner.client.infrastructure.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "authentication.service")
public class AuthenticationServiceProfile {

    private String exportUrl;
    private String updateUrl;

    public String getExportUrl() {
        return exportUrl;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setExportUrl(final String exportUrl) {
        this.exportUrl = exportUrl;
    }

    public void setUpdateUrl(final String updateUrl) {
        this.updateUrl = updateUrl;
    }
}
