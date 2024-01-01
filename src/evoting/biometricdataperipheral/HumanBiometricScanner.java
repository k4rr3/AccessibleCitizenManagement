package evoting.biometricdataperipheral;

import data.BiometricData;
import data.SingleBiometricData;
import exceptions.BiometricVerificationFailedException;
import exceptions.HumanBiometricScanningException;

/**
 * Peripherals for reading and scanning biometric data
 */

public interface HumanBiometricScanner {// Peripheral for scanning human biometrics

    BiometricData biometricData = null;

    SingleBiometricData scanFaceBiometrics()
            throws HumanBiometricScanningException;

    SingleBiometricData scanFingerprintBiometrics()
            throws HumanBiometricScanningException;

    default BiometricData getBiometricData() {
        return biometricData;
    }
}
