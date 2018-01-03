package com.pablo.acs.fingerprint.scanner.client.infrastructure.update;

import com.pablo.acs.fingerprint.scanner.client.domain.update.UpdateService;
import com.pablo.acs.fingerprint.scanner.client.domain.update.ports.incoming.UpdateDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class UpdateScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateScheduler.class);
    private final UpdateService updateService;

    @Autowired
    public UpdateScheduler(final UpdateService updateService) {
        this.updateService = updateService;
    }

    @Scheduled(fixedDelay = 15000)
    void updateDatabase() {
        LOGGER.info("Update database start on " + LocalDateTime.now());
        updateService.handle(
                new UpdateDatabase());
        LOGGER.info("Update database finished on " + LocalDateTime.now());
    }
}
