package com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming;

import java.util.Collection;

public interface SystemProfile {

    String getExportUrl();

    String getUpdateUrl();

    Collection<String> getNotificationEndpoints();

    String getSerialPort();
}
