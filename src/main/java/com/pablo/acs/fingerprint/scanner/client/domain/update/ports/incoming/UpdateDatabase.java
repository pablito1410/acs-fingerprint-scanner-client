package com.pablo.acs.fingerprint.scanner.client.domain.update.ports.incoming;

public class UpdateDatabase {

    private String centralDBTimestampUrl;
    private String updateUrl;

    public UpdateDatabase(final String centralDBTimestampUrl, final String updateUrl) {
        this.centralDBTimestampUrl = centralDBTimestampUrl;
        this.updateUrl = updateUrl;
    }

    public String getCentralDBTimestampUrl() {
        return centralDBTimestampUrl;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }
}
