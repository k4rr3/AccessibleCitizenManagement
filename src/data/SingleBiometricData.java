package data;

public class SingleBiometricData {
    private final byte[] biometricKey;

    public SingleBiometricData(byte[] biometricKey) {
        this.biometricKey = biometricKey;
    }

    public byte[] getBiometricKey() {
        return biometricKey;
    }
}
