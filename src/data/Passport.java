package data;

import exceptions.InvalidDNIDocumException;

public class Passport {

    public boolean isInForce = true;

    private SingleBiometricData facialBiomData;
    private SingleBiometricData fingerprintBiomData;

    public BiometricData biometricData;

    public Nif nif = new Nif("77711133X");

    public Passport(boolean isInForce, SingleBiometricData facialBiomData, SingleBiometricData fingerprintBiomData, String nif) throws InvalidDNIDocumException {
        this.isInForce = isInForce;
        this.facialBiomData = facialBiomData;
        this.fingerprintBiomData = fingerprintBiomData;
        this.biometricData = new BiometricData(facialBiomData, fingerprintBiomData);
        this.nif = new Nif(nif);
    }
}
