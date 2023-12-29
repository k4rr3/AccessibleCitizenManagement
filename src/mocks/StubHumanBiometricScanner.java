package mocks;

import data.SingleBiometricData;
import exceptions.HumanBiometricScanningException;

public class StubHumanBiometricScanner implements evoting.biometricdataperipheral.HumanBiometricScanner {
    @Override
    public SingleBiometricData scanFaceBiometrics(byte[] faceData) throws HumanBiometricScanningException {
        try {
            //implementation to scan face biometrics
            return new SingleBiometricData(faceData);
        } catch (Exception e) {
            throw new HumanBiometricScanningException("Error scanning face biometrics");
        }
    }
    
    @Override
    public SingleBiometricData scanFingerprintBiometrics(byte[] fingerprintData) throws HumanBiometricScanningException {
        try {
            //implementation to scan fingerprint biometrics
            return new SingleBiometricData(fingerprintData);
        } catch (Exception e) {
            throw new HumanBiometricScanningException("Error scanning fingerprint biometrics");
        }
    }
}
