package com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception;

import com.pablo.gt511c1r.exception.CommandProcessingException;

public class IdAlreadyExistException extends RuntimeException {

    public IdAlreadyExistException(final CommandProcessingException e) {
        super(e);
    }
}
