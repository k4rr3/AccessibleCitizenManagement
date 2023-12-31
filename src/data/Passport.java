package data;

import exceptions.InvalidDNIDocumException;

public class Passport {


    public boolean isInForce;

    public BiometricData biometricData;

    public Nif nif;
    private SingleBiometricData facialBiomData;
    private SingleBiometricData fingerprintBiomData;

    //==============For tests purposes=================================
    public void setInForce(boolean inForce) {
        isInForce = inForce;
    }

    public void setBiometricData(BiometricData biometricData) {
        this.biometricData = biometricData;
    }
    //==================================================================


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
