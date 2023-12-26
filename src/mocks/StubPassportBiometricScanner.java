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
    public Nif getNifWithOCR(String extractedNif) throws InvalidDNIDocumException {
        return new Nif(extractedNif);
    }

    @Override
    public BiometricData getPassportBiometricData(byte[] facialData, byte[] fingerprintData) throws PassportBiometricReadingException {
        try {
            SingleBiometricData facialBiomData = new SingleBiometricData(facialData);
            SingleBiometricData fingerprintBiomData = new SingleBiometricData(fingerprintData);
            return new BiometricData(facialBiomData, fingerprintBiomData);
        } catch (Exception e) {
            throw new PassportBiometricReadingException("Error reading passport biometric data");
        }
    }
}
