package mocks;

import data.BiometricData;
import data.SingleBiometricData;
import exceptions.HumanBiometricScanningException;

public class StubHumanBiometricScanner implements evoting.biometricdataperipheral.HumanBiometricScanner {


    private BiometricData biometricData;


    public StubHumanBiometricScanner(SingleBiometricData faceData, SingleBiometricData fingerprintData) {
        this.biometricData = new BiometricData(faceData, fingerprintData);
    }

    @Override
    public SingleBiometricData scanFaceBiometrics() throws HumanBiometricScanningException {
        if (biometricData.getFacialKey() == null)
            throw new HumanBiometricScanningException("Error scanning face biometrics");
        System.out.println("Biometría facial escaneada");
        return biometricData.getFacialKey();

    }

    @Override
    public SingleBiometricData scanFingerprintBiometrics() throws HumanBiometricScanningException {

        if (biometricData.getFingerPrintKey() == null)
            throw new HumanBiometricScanningException("\"Error scanning fingerprint biometrics");
        System.out.println("Biometría de la huella digital escaneada");
        return biometricData.getFingerPrintKey();
    }

    @Override
    public BiometricData getBiometricData() {
        return biometricData;
    }
}
