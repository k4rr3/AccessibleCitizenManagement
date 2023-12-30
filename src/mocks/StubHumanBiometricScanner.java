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
        try {
            System.out.println("Biometría facial escaneada");
            return biometricData.getFacialKey();

        } catch (Exception e) {
            throw new HumanBiometricScanningException("Error scanning face biometrics");
        }
    }

    @Override
    public SingleBiometricData scanFingerprintBiometrics() throws HumanBiometricScanningException {
        try {
            System.out.println("Biometría de la huella digital escaneada");
            return biometricData.getFingerPrintKey();
        } catch (Exception e) {
            throw new HumanBiometricScanningException("Error scanning fingerprint biometrics");
        }
    }

    @Override
    public BiometricData getBiometricData() {
        return biometricData;
    }
}
