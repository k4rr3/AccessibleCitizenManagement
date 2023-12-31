/*
package evoting;

import data.BiometricData;
import data.Nif;
import data.Passport;
import data.SingleBiometricData;
import evoting.VotingKiosk;
import evoting.biometricdataperipheral.HumanBiometricScanner;
import evoting.biometricdataperipheral.PassportBiometricReader;
import exceptions.*;
import mocks.StubHumanBiometricScanner;
import mocks.StubPassportBiometricReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BiometricDataVK {

    private VotingKiosk votingKiosk;
    private StubHumanBiometricScanner humanBiometricScanner;
    private StubPassportBiometricReader passportBiometricReader;

    @BeforeEach
    void setUp() throws InvalidDNIDocumException {
        humanBiometricScanner = new StubHumanBiometricScanner();
        passportBiometricReader = new StubPassportBiometricReader();
        votingKiosk = new VotingKiosk();
        votingKiosk.setHumanBiometricScanner(humanBiometricScanner);
        votingKiosk.setPassportBiometricReader(passportBiometricReader);
    }

    @Test
    void testVerifyBiometricDataMatching() {
        try {
            SingleBiometricData faceData = new SingleBiometricData(new byte[]{0x00, 0x42});
            SingleBiometricData fingerprintData = new SingleBiometricData(new byte[]{0x01, 0x02});
            BiometricData humanBiometricData = new BiometricData(faceData, fingerprintData);
            Passport passport = new Passport(true, faceData, fingerprintData, "99572958R");
            BiometricData passportBiometricData = passport.getBiometricData();

            votingKiosk.verifyBiometricData(humanBiometricData, passportBiometricData);
        } catch (Exception e) {
            fail("Exception not expected for matching biometric data");
        }
    }

    @Test
    void testVerifyBiometricDataMismatch() {
        try {
            SingleBiometricData faceData = new SingleBiometricData(new byte[]{0x00, 0x42});
            SingleBiometricData fingerprintData = new SingleBiometricData(new byte[]{0x01, 0x02});
            BiometricData humanBiometricData = new BiometricData(faceData, fingerprintData);
            Passport passport = new Passport(true, new SingleBiometricData(new byte[]{0x11, 0x22}), fingerprintData, "99572958R");
            BiometricData passportBiometricData = passport.getBiometricData();

            assertThrows(BiometricVerificationFailedException.class, () ->
                    votingKiosk.verifyBiometricData(humanBiometricData, passportBiometricData));
        } catch (Exception e) {
            fail("Exception expected for mismatching biometric data");
        }
    }

    @Test
    void testRemoveBiometricData() {
        try {
            SingleBiometricData faceData = new SingleBiometricData(new byte[]{0x00, 0x42});
            SingleBiometricData fingerprintData = new SingleBiometricData(new byte[]{0x01, 0x02});
            votingKiosk.verifyBiometricData(new BiometricData(faceData, fingerprintData), passportBiometricReader.getPassportBiometricData());

            votingKiosk.removeBiometricData();

            assertNull(votingKiosk.humanBioD);
            assertNull(votingKiosk.passpBioD);
        } catch (Exception e) {
            fail("Exception not expected for removing biometric data");
        }
    }
}
*/
