package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SingleBiometricDataTest {

    private SingleBiometricData biometricData;

    @BeforeEach
    public void setUp() {
        // Inicializar objetos necesarios antes de cada prueba
        byte[] sampleKey = new byte[]{1, 2, 3, 4, 5};
        biometricData = new SingleBiometricData(sampleKey);
    }

    @Test
    public void testGetBiometricKey() {
        // Verificar que el método getBiometricKey devuelve el mismo array de bytes
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5}, biometricData.getBiometricKey());
    }

    @Test
    public void testGetBiometricKeyNotNull() {
        // Verificar que el método getBiometricKey no devuelve un valor nulo
        assertNotNull(biometricData.getBiometricKey());
    }
}
