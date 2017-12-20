package com.pablo.acs.fingerprint.scanner.client.domain.export;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.ExportFingerprintTemplate;
import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.ExportParams;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing.RestClient;

public class ExportService {

    private final RestClient restClientDefault;
    private final ExportParams exportParams;

    public ExportService(final RestClient restClient,
                         final ExportParams exportParams) {
        this.restClientDefault = restClient;
        this.exportParams = exportParams;
    }

    public void exportFingerprintTemplate(final ExportFingerprintTemplate command) {
        final AddUserIdentifyMethod addIdentifyMethodCommand =
                new AddUserIdentifyMethod(command.getUserId(), command.getTemplate());
        restClientDefault.post(exportParams.exportUrl(), addIdentifyMethodCommand);
    }

}
