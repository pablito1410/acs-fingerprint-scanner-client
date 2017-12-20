package com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming;

import com.pablo.acs.fingerprint.scanner.client.domain.command.Command;

public class GetTemplate implements Command {

    private int id;

    public GetTemplate(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
