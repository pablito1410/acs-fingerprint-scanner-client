package com.pablo.acs.fingerprint.sanner.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FingerprintScannerClient {

    public static void main(final String[] args) {
        SpringApplication.run(FingerprintScannerClient.class);
    }
}
