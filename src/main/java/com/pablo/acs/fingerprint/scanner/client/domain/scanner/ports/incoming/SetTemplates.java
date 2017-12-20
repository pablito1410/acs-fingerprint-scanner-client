package com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming;

import com.pablo.acs.fingerprint.scanner.client.domain.command.Command;

import java.util.Map;

public class SetTemplates implements Command {

    private Map<Long, byte[]> templates;

    public SetTemplates(final Map<Long, byte[]> templates) {
        this.templates = templates;
    }

    public Map<Long, byte[]> getTemplates() {
        return templates;
    }
}
