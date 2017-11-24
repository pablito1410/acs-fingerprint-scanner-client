package com.pablo.acs.fingerprint.scanner.client.infrastructure.profile;

import com.pablo.acs.fingerprint.scanner.client.domain.ports.incoming.param.NotificationParams;

import java.util.Collection;
import java.util.Collections;

public class NotificationProfile implements NotificationParams {

    @Override
    public Collection<String> getEndpoints() {
        return Collections.emptyList();
    }
}
