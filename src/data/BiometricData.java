package data;

public class BiometricData {
    //Equivalente a una clase Passport
    private SingleBiometricData facialKey;
    private SingleBiometricData fingerPrintKey;

    public BiometricData(SingleBiometricData facialKey, SingleBiometricData fingerprintKey) {
        this.facialKey = facialKey;
        this.fingerPrintKey = fingerprintKey;
    }

    public void deleteAllInfo() {
        facialKey = null;
        fingerPrintKey = null;

    }

    public SingleBiometricData getFacialKey() {
        return facialKey;
    }

    public SingleBiometricData getFingerPrintKey() {
        return fingerPrintKey;
    }
}
