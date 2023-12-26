package data;

public class BiometricData {
    //Equivalente a una clase Passport
    private final SingleBiometricData facialKey;
    private final SingleBiometricData fingerPrintlKey;

    public BiometricData(SingleBiometricData facialKey, SingleBiometricData fingerprintKey) {
        this.facialKey = facialKey;
        this.fingerPrintlKey = fingerprintKey;
    }
}
