package data;

import java.util.Arrays;

public class SingleBiometricData {
    private final byte[] biometricKey;

    public SingleBiometricData(byte[] biometricKey) {
        this.biometricKey = biometricKey;
    }

    public byte[] getBiometricKey() {
        return biometricKey;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SingleBiometricData sbData = (SingleBiometricData) obj;
        return Arrays.equals(sbData.getBiometricKey(), this.getBiometricKey());
    }
}
