package data;

public class BiometricData {
    //Equivalente a una clase Passport
    private SingleBiometricData facialKey;
    private SingleBiometricData fingerPrintlKey;

    public BiometricData(SingleBiometricData facialKey, SingleBiometricData fingerprintKey) {
        this.facialKey = facialKey;
        this.fingerPrintlKey = fingerprintKey;
    }

    public void deleteAllInfo() {
        facialKey = null;
        fingerPrintlKey = null;

    }
}
