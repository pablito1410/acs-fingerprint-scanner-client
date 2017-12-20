package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.pablo.acs.fingerprint.scanner.client.infrastructure.profile.AuthenticationServiceProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileConfig {

    @Bean
    public AuthenticationServiceProfile authenticationServiceProfile() {
        return new AuthenticationServiceProfile();
    }
}
