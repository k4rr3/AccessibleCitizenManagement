package evoting.biometricdataperipheral;

import data.SingleBiometricData;
import exceptions.BiometricVerificationFailedException;
import exceptions.HumanBiometricScanningException;

/**
 * Peripherals for reading and scanning biometric data
 */

public interface HumanBiometricScanner {// Peripheral for scanning human biometrics
    SingleBiometricData scanFaceBiometrics (byte[] faceData)
            throws HumanBiometricScanningException;
    SingleBiometricData scanFingerprintBiometrics (byte[] fingerprintData)
            throws HumanBiometricScanningException;
}
