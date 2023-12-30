package mocks;

import data.BiometricData;
import data.Nif;
import data.Passport;
import data.SingleBiometricData;
import evoting.biometricdataperipheral.PassportBiometricReader;
import exceptions.InvalidDNIDocumException;
import exceptions.NotValidPassportException;
import exceptions.PassportBiometricReadingException;

public class StubPassportBiometricReader implements PassportBiometricReader {




    private Passport passport;

    public Passport getPassport() {
        return passport;
    }
    public Nif getNif() {return passport.nif;}

    public StubPassportBiometricReader() throws InvalidDNIDocumException {
        passport = new Passport(true, new SingleBiometricData(new byte[]{0x00, 0x42}), new SingleBiometricData(new byte[]{0x01, 0x02}), "99572958R");
    }

    @Override
    public void validatePassport() throws NotValidPassportException {
        System.out.println("Leyendo el pasaporte...");
        if (!passport.isInForce || passport.biometricData == null) {
            throw new NotValidPassportException("Invalid passport number");
        }
        System.out.println("El pasaporte leído es válido");
    }

    @Override
    public Nif getNifWithOCR() throws InvalidDNIDocumException {
        System.out.println("Obteniendo el NIF con OCR...");
        System.out.println("NIF obtenido correctamente");
        return passport.nif;

    }

    @Override
    public BiometricData getPassportBiometricData() throws PassportBiometricReadingException {
        System.out.println("Obteniendo datos biométricos del pasaporte...");
        System.out.println("Datos biométricos del pasaporte leídos correctamente");
        return passport.biometricData;


    }
}
