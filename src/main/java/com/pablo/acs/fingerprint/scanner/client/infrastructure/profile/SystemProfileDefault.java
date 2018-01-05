package com.pablo.acs.fingerprint.scanner.client.infrastructure.profile;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.SystemProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class SystemProfileDefault implements SystemProfile {

    private final AuthenticationServiceProfile authenticationServiceProfile;
    private final NotificationProfile notificationProfile;
    private final SerialPortProfile serialPortProfile;

    @Autowired
    public SystemProfileDefault(final AuthenticationServiceProfile authenticationServiceProfile,
                                final NotificationProfile notificationProfile,
                                final SerialPortProfile serialPortProfile) {
        this.authenticationServiceProfile = authenticationServiceProfile;
        this.notificationProfile = notificationProfile;
        this.serialPortProfile = serialPortProfile;
    }

    @Override
    public String getExportUrl() {
        return authenticationServiceProfile.getExportUrl();
    }

    @Override
    public String getUpdateUrl() {
        return authenticationServiceProfile.getUpdateUrl();
    }

    @Override
    public Collection<String> getNotificationEndpoints() {
        return notificationProfile.getEndpoints();
    }

    @Override
    public String getSerialPort() {
        return serialPortProfile.getSerialPort();
    }


}
