package com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming;

import com.pablo.acs.fingerprint.scanner.client.domain.command.Command;

public class ExportFingerprintTemplate implements Command {

    private int userId;
    private byte[] template;

    public ExportFingerprintTemplate(final int userId, final byte[] template) {
        this.userId = userId;
        this.template = template;
    }

    public int getUserId() {
        return userId;
    }

    public byte[] getTemplate() {
        return template;
    }
}
