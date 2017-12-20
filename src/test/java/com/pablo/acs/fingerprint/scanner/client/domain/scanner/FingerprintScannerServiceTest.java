package com.pablo.acs.fingerprint.scanner.client.domain.scanner;

import com.pablo.acs.fingerprint.scanner.client.domain.export.ExportService;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.FingerIsNotPressed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.exception.IdentificationFailed;
import com.pablo.acs.fingerprint.scanner.client.domain.scanner.ports.outgoing.FingerprintScannerApi;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.config.FingerprintScannerTestConfig;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.config.NotificationTestConfig;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.config.RestConfig;
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
        FingerprintScannerTestConfig.class,
        NotificationTestConfig.class,
        RestConfig.class})
public class FingerprintScannerServiceTest {

    @Mock
    private FingerprintScannerApi fingerprintScannerApi;

    @Autowired
    private NotificationSender notificationSender;
    private FingerprintScannerService fingerprintScannerService;
    private ExportService exportService;

    @Before
    public void setUp() throws Exception, FingerIsNotPressed, IdentificationFailed {
        Mockito.when(fingerprintScannerApi.fingerIsPressed()).thenReturn(Boolean.TRUE);
        Mockito
                .when(fingerprintScannerApi.identify())
                .thenReturn(IdentificationResult.success(1));
        fingerprintScannerService = new FingerprintScannerService(fingerprintScannerApi, notificationSender, exportService);

    }

    @Test
    public void onFingerPressedTest() {
        Mockito.when(fingerprintScannerService.isFingerPressed()).thenReturn(Boolean.TRUE);
        fingerprintScannerService.onFingerPressed();
    }
}