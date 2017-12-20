package com.pablo.acs.fingerprint.scanner.client.domain.ports.incoming.param;

public interface Response<T> {
    int getStatusCode();

    T getBody();
}
