package evoting.biometricdataperipheral;

import data.BiometricData;
import data.Nif;
import exceptions.InvalidDNIDocumException;
import exceptions.NotValidPassportException;
import exceptions.PassportBiometricReadingException;

public interface PassportBiometricReader {// Perip. for reading passport biometrics
    void validatePassport () throws NotValidPassportException;
    Nif getNifWithOCR (String Nif) throws InvalidDNIDocumException;
    BiometricData getPassportBiometricData (byte[] facialData, byte[] fingerprintData)
            throws PassportBiometricReadingException;
}