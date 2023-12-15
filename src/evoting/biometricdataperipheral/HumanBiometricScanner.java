package evoting.biometricdataperipheral;

import data.SingleBiometricData;
import exceptions.HumanBiometricScanningException;

/**
 * Peripherals for reading and scanning biometric data
 */

public interface HumanBiometricScanner {// Peripheral for scanning human biometrics
    SingleBiometricData scanFaceBiometrics ()
            throws HumanBiometricScanningException;
    SingleBiometricData scanFingerprintBiometrics ()
            throws HumanBiometricScanningException;
}
