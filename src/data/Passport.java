package data;

import exceptions.InvalidDNIDocumException;

public class Passport {

    public boolean isInForce;

    private SingleBiometricData facialBiomData;
    private SingleBiometricData fingerprintBiomData;


    public BiometricData biometricData;

    public Nif nif;

    public Passport(boolean isInForce, SingleBiometricData facialBiomData, SingleBiometricData fingerprintBiomData, String nif) throws InvalidDNIDocumException {
        this.isInForce = isInForce;
        this.facialBiomData = facialBiomData;
        this.fingerprintBiomData = fingerprintBiomData;
        this.biometricData = new BiometricData(facialBiomData, fingerprintBiomData);
        this.nif = new Nif(nif);
    }

    public BiometricData getBiometricData() {
        return biometricData;
    }
}
