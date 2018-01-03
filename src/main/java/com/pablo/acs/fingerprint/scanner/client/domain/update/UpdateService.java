package com.pablo.acs.fingerprint.scanner.client.domain.update;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.SystemProfile;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.incoming.param.Response;
import com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing.RestClient;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.FingerprintScannerService;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming.SetTemplates;
import com.pablo.acs.fingerprint.scanner.client.domain.update.exception.AuthenticationServiceConnectionError;
import com.pablo.acs.fingerprint.scanner.client.domain.update.ports.incoming.UpdateDatabase;
import com.pablo.acs.fingerprint.scanner.client.domain.update.ports.outgoing.FingerprintTemplate;
import com.pablo.acs.fingerprint.scanner.client.domain.update.ports.outgoing.NewTemplatesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateService.class);
    private final RestClient restClient;
    private final FingerprintScannerService fingerprintScannerService;
    private final SystemProfile systemProfile;

    public UpdateService(final RestClient restClient,
                         final FingerprintScannerService fingerprintScannerService,
                         final SystemProfile systemProfile) {
        this.restClient = restClient;
        this.fingerprintScannerService = fingerprintScannerService;
        this.systemProfile = systemProfile;
    }

    public void handle(final UpdateDatabase command) {
        LOGGER.info("Database update process started.");

        Map<Long, byte[]> templates = downloadNewTemplates(
                String.format(systemProfile.getUpdateUrl(), UpdateContext.LAST_UPDATE))
                    .stream()
                    .collect(Collectors.toMap(
                            FingerprintTemplate::getUserId, FingerprintTemplate::getIdentifier));

        if (templates.size() > 0) {
            fingerprintScannerService.handle(new SetTemplates(templates));
        } else {
            LOGGER.info("No changes in central database.");
        }
        UpdateContext.LAST_UPDATE = LocalDateTime.now();
        LOGGER.info("Database update process finished.");
    }

    private Collection<FingerprintTemplate> downloadNewTemplates(final String url) {
        LOGGER.info("Downloading new templates...");
        final Response<NewTemplatesDTO> response =
                restClient.get(url, NewTemplatesDTO.class);

        if (response.getStatusCode() == 200) {
            return response.getBody().getIdentifiers();
        } else {
            LOGGER.warn("Auth service returned HttpStatus=" + response.getStatusCode());
            throw new AuthenticationServiceConnectionError("HttpStatus=" + response.getStatusCode());
        }
    }

    private LocalDateTime centralDatabaseTimestamp(final String url) {
        LOGGER.info("Getting central database timestamp...");
        final Response<LocalDateTime> response = restClient.get(url, LocalDateTime.class);
        if (response.getStatusCode() == 200) {
            return response.getBody();
        } else {
            LOGGER.warn("Auth service returned HttpStatus=" + response.getStatusCode());
            throw new AuthenticationServiceConnectionError("HttpStatus=" + response.getStatusCode());
        }
    }
}
