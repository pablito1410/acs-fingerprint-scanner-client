package com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming;

import com.pablo.acs.fingerprint.scanner.client.domain.command.Command;

public class EnrollCommand implements Command {

    private Integer userId;

    private EnrollCommand() { }

    public Integer getUserId() {
        return userId;
    }

}
