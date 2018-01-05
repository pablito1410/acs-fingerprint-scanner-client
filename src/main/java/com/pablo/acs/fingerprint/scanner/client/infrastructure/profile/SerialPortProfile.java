package com.pablo.acs.fingerprint.scanner.client.infrastructure.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "scanner")
public class SerialPortProfile {

    private String serialPort;

    public String getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(final String serialPort) {
        this.serialPort = serialPort;
    }
}
