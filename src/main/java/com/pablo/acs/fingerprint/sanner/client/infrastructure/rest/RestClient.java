package com.pablo.acs.fingerprint.sanner.client.infrastructure.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {

    private final RestTemplate restTemplate;

    public RestClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity post(final String url, final Object json) {
        ResponseEntity response = restTemplate.postForObject(url, json, ResponseEntity.class);
        return response;
    }
}
