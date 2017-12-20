package com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming;

import com.pablo.acs.fingerprint.scanner.client.domain.command.Command;

public class EnrollCommand implements Command {

    private int userId;

    private EnrollCommand() { }

    public int getUserId() {
        return userId;
    }

}
