package mocks;

import data.BiometricData;
import data.Nif;
import data.SingleBiometricData;
import evoting.biometricdataperipheral.PassportBiometricReader;
import exceptions.InvalidDNIDocumException;
import exceptions.NotValidPassportException;
import exceptions.PassportBiometricReadingException;

public class StubPassportBiometricScanner implements PassportBiometricReader {
    private String passportNumber; // Assuming you need to store the passport number

    // Constructor
    public StubPassportBiometricScanner(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public void validatePassport() throws NotValidPassportException {
        if (passportNumber == null || passportNumber.isEmpty()) {
            throw new NotValidPassportException("Invalid passport number");
        }
    }

    @Override
    public Nif getNifWithOCR() throws InvalidDNIDocumException {
        return new Nif("fakeNif");
    }

    @Override
    public BiometricData getPassportBiometricData() throws PassportBiometricReadingException {
        try {
            SingleBiometricData facialBiomData = new SingleBiometricData(new byte[]{0x00,0x42});
            SingleBiometricData fingerprintBiomData = new SingleBiometricData(new byte[]{0x01,0x02});
            return new BiometricData(facialBiomData, fingerprintBiomData);
        } catch (Exception e) {
            throw new PassportBiometricReadingException("Error reading passport biometric data");
        }
    }
}
