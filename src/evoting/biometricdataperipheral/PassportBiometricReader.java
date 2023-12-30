package evoting.biometricdataperipheral;

import data.BiometricData;
import data.Nif;
import data.Passport;
import exceptions.InvalidDNIDocumException;
import exceptions.NotValidPassportException;
import exceptions.PassportBiometricReadingException;

public interface PassportBiometricReader {// Perip. for reading passport biometrics
    Passport passport = null;

    default Passport getPassport() {
        return passport;
    }
    void validatePassport () throws NotValidPassportException;
    Nif getNifWithOCR () throws InvalidDNIDocumException;
    BiometricData getPassportBiometricData ()
            throws PassportBiometricReadingException;
}