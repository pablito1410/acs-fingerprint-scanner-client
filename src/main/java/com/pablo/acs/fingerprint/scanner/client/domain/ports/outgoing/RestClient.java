package com.pablo.acs.fingerprint.scanner.client.domain.ports.outgoing;

import com.pablo.acs.fingerprint.scanner.client.domain.ports.incoming.param.Response;

import java.util.Map;

public interface RestClient {

    <R> Response<R> post(String url, Object body, Class<R> response);

    Response<Void> post(String url, Object body);

    <R> Response<R> get(String url, Class<R> response);

    <R> Response<R> get(String url, Class<R> response, Map<String, ?> uriVariables);

}
