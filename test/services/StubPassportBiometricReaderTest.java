package services;

import data.BiometricData;
import data.Nif;
import data.Passport;
import exceptions.InvalidDNIDocumException;
import exceptions.NotValidPassportException;
import exceptions.PassportBiometricReadingException;
import mocks.StubPassportBiometricReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StubPassportBiometricReaderTest {

    private StubPassportBiometricReader passportBiometricReader;

    @BeforeEach
    void setUp() throws InvalidDNIDocumException {
        passportBiometricReader = new StubPassportBiometricReader();
    }

    @Test
    void testValidatePassport() {
        try {
            passportBiometricReader.validatePassport();
        } catch (NotValidPassportException e) {
            fail("Exception not expected for a valid passport");
        }
    }

    @Test
    void testInvalidPassport() {
        Passport passport = passportBiometricReader.getPassport();
        passport.setInForce(false);
        assertThrows(NotValidPassportException.class, () -> passportBiometricReader.validatePassport());
    }

    @Test
    void testGetNifWithOCR() {
        try {
            Nif nif = passportBiometricReader.getNifWithOCR();
            assertNotNull(nif);
            assertEquals("99572958R", nif.getNif());
        } catch (InvalidDNIDocumException e) {
            fail("Exception not expected during NIF retrieval with OCR");
        }
    }

    @Test
    void testGetPassportBiometricData() {
        try {
            BiometricData biometricData = passportBiometricReader.getPassportBiometricData();
            assertNotNull(biometricData);
            assertArrayEquals(new byte[]{0x00, 0x42}, biometricData.getFacialKey().getBiometricKey());
            assertArrayEquals(new byte[]{0x01, 0x02}, biometricData.getFingerPrintKey().getBiometricKey());
        } catch (PassportBiometricReadingException e) {
            fail("Exception not expected during passport biometric data retrieval");
        }
    }

    @Test
    void testPassportBiometricReadingException() {
        passportBiometricReader.getPassport().setBiometricData(null);

        assertThrows(PassportBiometricReadingException.class, () -> passportBiometricReader.getPassportBiometricData());
    }
}
