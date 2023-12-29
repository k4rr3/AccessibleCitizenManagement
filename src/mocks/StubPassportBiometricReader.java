package mocks;

import data.BiometricData;
import data.Nif;
import data.SingleBiometricData;
import evoting.biometricdataperipheral.PassportBiometricReader;
import exceptions.InvalidDNIDocumException;
import exceptions.NotValidPassportException;
import exceptions.PassportBiometricReadingException;

public class StubPassportBiometricReader implements PassportBiometricReader {
    private String passportNumber;
    private SingleBiometricData facialBiomData;
    private SingleBiometricData fingerprintBiomData;

    // Constructor
    public StubPassportBiometricReader() {
    }

    @Override
    public void validatePassport() throws NotValidPassportException {
        System.out.println("Leyendo el pasaporte...");
        if (passportNumber == null || passportNumber.isEmpty()) {
            throw new NotValidPassportException("Invalid passport number");
        }
        System.out.println("El pasaporte leído es válido");
    }

    @Override
    public Nif getNifWithOCR() throws InvalidDNIDocumException {
        System.out.println("Obteniendo datos biométricos del pasaporte...");
        return new Nif("fakeNif");
    }

    @Override
    public BiometricData getPassportBiometricData() throws PassportBiometricReadingException {
        System.out.println("Obteniendo datos biométricos del pasaporte...");
        try {
            facialBiomData = new SingleBiometricData(new byte[]{0x00, 0x42});
            fingerprintBiomData = new SingleBiometricData(new byte[]{0x01, 0x02});
            BiometricData biometricData = new BiometricData(facialBiomData, fingerprintBiomData);
            System.out.println("Datos biométricos del pasaporte leídos correctamente");
            return biometricData;
        } catch (Exception e) {
            throw new PassportBiometricReadingException("Error reading passport biometric data");
        }

    }
}
