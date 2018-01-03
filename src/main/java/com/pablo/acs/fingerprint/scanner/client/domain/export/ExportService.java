package com.pablo.acs.fingerprint.scanner.client.domain.export;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.ExportFingerprintTemplate;
import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.SystemProfile;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing.RestClient;

public class ExportService {

    private final RestClient restClientDefault;
    private final SystemProfile systemProfile;

    public ExportService(final RestClient restClient,
                         final SystemProfile systemProfile) {
        this.restClientDefault = restClient;
        this.systemProfile = systemProfile;
    }

    public void handle(final ExportFingerprintTemplate command) {
        final AddUserIdentifyMethod addIdentifyMethodCommand =
                new AddUserIdentifyMethod(command.getUserId(), command.getTemplate());
        restClientDefault.post(systemProfile.getExportUrl(), addIdentifyMethodCommand);
    }

}
