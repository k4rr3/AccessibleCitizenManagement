package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


public class BiometricDataTest {
    private BiometricData biometricData;

    @BeforeEach
    public void setUp() {
        // Configuración inicial antes de cada prueba
        SingleBiometricData facialKey = new SingleBiometricData(new byte[]{0x11, 0x22, 0x33, 0x44});
        SingleBiometricData fingerprintKey = new SingleBiometricData(new byte[]{0x12, 0x23, 0x34, 0x45});
        biometricData = new BiometricData(facialKey, fingerprintKey);
    }

    @Test
    public void testDeleteAllInfo() {
        // Prueba que el método deleteAllInfo funcione correctamente
        biometricData.deleteAllInfo();
        assertNull(biometricData.getFacialKey());
        assertNull(biometricData.getFingerPrintKey());
    }

    @Test
    public void testGetFacialKey() {
        // Prueba que el método getFacialKey devuelva la clave facial correcta
        SingleBiometricData facialKey = biometricData.getFacialKey();
        assertNotNull(facialKey);
        assertArrayEquals(new byte[]{0x11, 0x22, 0x33, 0x44}, facialKey.getBiometricKey());
    }

    @Test
    public void testGetFingerPrintKey() {
        // Prueba que el método getFingerPrintlKey devuelva la clave de huella dactilar correcta
        SingleBiometricData fingerprintKey = biometricData.getFingerPrintKey();
        assertNotNull(fingerprintKey);
        assertArrayEquals(new byte[]{0x12, 0x23, 0x34, 0x45}, fingerprintKey.getBiometricKey());
    }

    @Test
    public void testNullFacialKeyAfterDeleteAllInfo() {
        // Prueba que el método deleteAllInfo establece la clave facial en null
        biometricData.deleteAllInfo();
        assertNull(biometricData.getFacialKey());
    }

    @Test
    public void testNullFingerprintKeyAfterDeleteAllInfo() {
        // Prueba que el método deleteAllInfo establece la clave de huella dactilar en null
        biometricData.deleteAllInfo();
        assertNull(biometricData.getFingerPrintKey());
    }
}
