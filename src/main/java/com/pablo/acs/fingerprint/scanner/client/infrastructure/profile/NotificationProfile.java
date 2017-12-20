package com.pablo.acs.fingerprint.scanner.client.infrastructure.profile;

import com.pablo.acs.fingerprint.scanner.client.domain.ports.incoming.param.NotificationParams;

import java.util.ArrayList;
import java.util.Collection;

public class NotificationProfile implements NotificationParams {

    @Override
    public Collection<String> getEndpoints() {
        final Collection<String> endpoints = new ArrayList<>();
        endpoints.add("http://localhost:5000/");
        return endpoints;
    }
}
