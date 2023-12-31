package services;

import data.BiometricData;
import data.SingleBiometricData;
import exceptions.HumanBiometricScanningException;
import mocks.StubHumanBiometricScanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StubHumanBiometricScannerTest {

    private StubHumanBiometricScanner biometricScanner;
    byte[] facialData = new byte[]{0x00, 0x42};
    byte[] fingerData = new byte[]{0x01, 0x02};

    @BeforeEach
    void setUp() {
        SingleBiometricData faceData = new SingleBiometricData(facialData);
        SingleBiometricData fingerprintData = new SingleBiometricData(fingerData);
        biometricScanner = new StubHumanBiometricScanner(faceData, fingerprintData);
    }

    @Test
    void testScanFaceBiometrics() {
        try {
            SingleBiometricData faceData = biometricScanner.scanFaceBiometrics();
            assertNotNull(faceData);
            assertArrayEquals(facialData, faceData.getBiometricKey());
        } catch (HumanBiometricScanningException e) {
            fail("Exception not expected during face biometrics scanning");
        }
    }

    @Test
    void testScanFingerprintBiometrics() {
        try {
            SingleBiometricData fingerprintData = biometricScanner.scanFingerprintBiometrics();
            assertNotNull(fingerprintData);
            assertArrayEquals(fingerData, fingerprintData.getBiometricKey());
        } catch (HumanBiometricScanningException e) {
            fail("Exception not expected during fingerprint biometrics scanning");
        }
    }

    @Test
    void testScanFaceBiometricsException() {
        biometricScanner = new StubHumanBiometricScanner(null, new SingleBiometricData(fingerData));


        assertThrows(HumanBiometricScanningException.class, () -> biometricScanner.scanFaceBiometrics());
    }

    @Test
    void testScanFingerprintBiometricsException() {
        biometricScanner = new StubHumanBiometricScanner(new SingleBiometricData(facialData), null);
        BiometricData biometricData = null;
        assertThrows(HumanBiometricScanningException.class, () -> biometricScanner.scanFingerprintBiometrics());
    }

    @Test
    void testGetBiometricData() {
        BiometricData biometricData = biometricScanner.getBiometricData();
        assertNotNull(biometricData);
        assertArrayEquals(facialData, biometricData.getFacialKey().getBiometricKey());
        assertArrayEquals(fingerData, biometricData.getFingerPrintKey().getBiometricKey());
    }
}
