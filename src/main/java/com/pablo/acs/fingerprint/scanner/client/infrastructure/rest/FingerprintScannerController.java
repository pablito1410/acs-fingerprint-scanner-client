package com.pablo.acs.fingerprint.scanner.client.infrastructure.rest;

import com.pablo.acs.fingerprint.scanner.client.domain.scanner.FingerprintScannerService;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming.EnrollCommand;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.incoming.GetTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fingerprint-scanner")
public class FingerprintScannerController {

    private final FingerprintScannerService fingerprintScannerService;

    public FingerprintScannerController(final FingerprintScannerService fingerprintScannerService) {
        this.fingerprintScannerService = fingerprintScannerService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/enroll", method = RequestMethod.POST)
    public ResponseEntity enroll(@RequestBody final EnrollCommand command) {
        fingerprintScannerService.handle(command);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/template", method = RequestMethod.GET)
    public ResponseEntity<String> template(@RequestParam("id") final int id) {
        final String template = fingerprintScannerService.getTemplate(new GetTemplate(id));
        return new ResponseEntity<>(template, HttpStatus.OK);
    }

}
