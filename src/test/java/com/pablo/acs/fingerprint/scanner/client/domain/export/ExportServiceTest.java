package com.pablo.acs.fingerprint.scanner.client.domain.export;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ports.incoming.ExportFingerprintTemplate;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing.FingerprintScannerApi;
import com.pablo.acs.fingerprint.scanner.client.domain.update.UpdateService;
import com.pablo.acs.fingerprint.scanner.client.domain.update.ports.incoming.UpdateDatabase;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.config.ExportServiceTestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        ExportServiceTestConfig.class})
public class ExportServiceTest {

    private final static int USER_ID = 5;
    private final static byte[] TEMPLATE = String.valueOf("123456789").getBytes();

    @Autowired
    private ExportService exportService;
    @Autowired
    private UpdateService updateService;
    @Autowired
    private FingerprintScannerApi fingerprintScanner;

    @Mock
    private ExportFingerprintTemplate exportCommand;
    @Mock
    private UpdateDatabase updateCommand;

    @Before
    public void setUp() {
    }

    @Test
    public void exportFingerprintTemplate() throws Exception {
        Mockito.when(exportCommand.getUserId()).thenReturn(USER_ID);
        Mockito.when(exportCommand.getTemplate()).thenReturn(TEMPLATE);

        exportService.handle(exportCommand);
        updateService.handle(updateCommand);

        byte[] actual = fingerprintScanner.getTemplate(exportCommand.getUserId());
        Assert.assertArrayEquals(TEMPLATE, actual);
    }

}