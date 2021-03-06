package com.pablo.acs.fingerprint.scanner.client.infrastructure.rest.error;

import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.FingerprintScannerException;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.IdAlreadyExistException;
import com.pablo.gt511c1r.exception.SerialPortIsNotOpenedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FingerprintScannerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(FingerprintScannerAdvice.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(FingerprintScannerException.class)
    public ErrorMessage handle(final FingerprintScannerException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(IdAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    public void handle(final IdAlreadyExistException e) {
//        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(SerialPortIsNotOpenedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorMessage handle() {
        LOGGER.warn("Serial port is not opened. Check module connection.");
        return new ErrorMessage("Serial port is not opened. Check module connection.");
    }
}
