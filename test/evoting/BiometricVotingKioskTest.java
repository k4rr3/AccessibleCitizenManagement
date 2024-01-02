package evoting;

import data.BiometricData;
import data.SingleBiometricData;
import exceptions.*;
import mocks.StubElectoralOrganism;
import mocks.StubHumanBiometricScanner;
import mocks.StubLocalService;
import mocks.StubPassportBiometricReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BiometricVotingKioskTest {

    private VotingKiosk votingKiosk;

    @BeforeEach
    public void setUp() throws ProceduralException, InvalidDNIDocumException {
        // En todos los test queremos usar la funcionalidad de e-voting
        votingKiosk = new VotingKiosk();
        votingKiosk.setLocalService(new StubLocalService());
        votingKiosk.setOption(1);
        votingKiosk.setOpt('p');
        votingKiosk.setExplicitConsent('y');
        votingKiosk.initVoting();


        StubElectoralOrganism electoralOrganism = new StubElectoralOrganism();
        votingKiosk.setElectoralOrganism(electoralOrganism);

        StubPassportBiometricReader passportBiometricReader = new StubPassportBiometricReader();
        votingKiosk.setPassportBiometricReader(passportBiometricReader);


        SingleBiometricData faceData = new SingleBiometricData(new byte[]{0x00, 0x42});
        SingleBiometricData fingerPrintData = new SingleBiometricData(new byte[]{0x01, 0x02});
        StubHumanBiometricScanner humanBiometricScanner = new StubHumanBiometricScanner(faceData, fingerPrintData);
        votingKiosk.setHumanBiometricScanner(humanBiometricScanner);
    }

    @Test
    public void testProceduralInit() throws ProceduralException {
        votingKiosk.setDocument('p');
        assertThrows(ProceduralException.class, () -> votingKiosk.initVoting());
    }

    @Test
    public void explicitConsentGiven() {
        votingKiosk.setExplicitConsent('n');
        assertDoesNotThrow(() -> votingKiosk.setDocument('p'));

    }

    @Test
    public void explicitConsentNotGiven() {
        votingKiosk.setExplicitConsent('y');
        assertDoesNotThrow(() -> votingKiosk.setDocument('p'));
    }

    @Test
    public void explicitConsentIncorrectFormat() {
        votingKiosk.setExplicitConsent('x');
        assertThrows(ProceduralException.class, () -> votingKiosk.setDocument('p'));
    }

    @Test
    public void correctPassportRead() throws ProceduralException {
        votingKiosk.setDocument('p');
        assertDoesNotThrow(() -> votingKiosk.readPassport());
    }

    @Test
    public void invalidPassport() {

    }

    @Test
    public void incorrectPassportReadNif() {

    }

    @Test
    public void incorrectPassportReadBioData() {

    }

    // OCR test?? ns nocal no

    @Test
    public void readFaceBioCorrectly() throws ProceduralException, PassportBiometricReadingException, InvalidDNIDocumException, NotValidPassportException, HumanBiometricScanningException {
        votingKiosk.setDocument('p');
        votingKiosk.readPassport();
        votingKiosk.readFaceBiometrics();
    }

    @Test
    public void readFingerBioCorrectly() throws ProceduralException, HumanBiometricScanningException, BiometricVerificationFailedException, NotEnabledException, ConnectException, PassportBiometricReadingException, InvalidDNIDocumException, NotValidPassportException {
        votingKiosk.setDocument('p');
        votingKiosk.readPassport();
        votingKiosk.readFaceBiometrics();
        votingKiosk.readFingerPrintBiometrics();
    }

    @Test
    public void verifyBioDataCorrectly() throws ProceduralException, HumanBiometricScanningException, PassportBiometricReadingException, InvalidDNIDocumException, NotValidPassportException {
        votingKiosk.setDocument('p');
        votingKiosk.readPassport();
        votingKiosk.readFaceBiometrics();
        // Doesn't throw BiometricVerificationFailedException
        assertDoesNotThrow(() -> votingKiosk.readFingerPrintBiometrics());
    }

    @Test
    public void verifyWrongBioData() throws ProceduralException, PassportBiometricReadingException, InvalidDNIDocumException, NotValidPassportException, HumanBiometricScanningException, BiometricVerificationFailedException, NotEnabledException, ConnectException {
        votingKiosk.setDocument('p');
        votingKiosk.readPassport();
        votingKiosk.readFaceBiometrics();

        //Fake biometric data for testing purposes [Test for when the data does not match]

        SingleBiometricData fakeHumanSBD = new SingleBiometricData(new byte[]{});
        BiometricData fakeBioDataHuman = new BiometricData(fakeHumanSBD, fakeHumanSBD);

        SingleBiometricData fakePassportSBD = new SingleBiometricData(new byte[]{0x0f});
        BiometricData fakeBioDataPassport = new BiometricData(fakePassportSBD, fakePassportSBD);

        votingKiosk.setHumanBioD(fakeBioDataHuman);
        votingKiosk.setPasspBioD(fakeBioDataPassport);

        assertThrows(BiometricVerificationFailedException.class, () -> votingKiosk.readFingerPrintBiometrics());
    }

    @Test
    public void removeBioData() throws ProceduralException, PassportBiometricReadingException, InvalidDNIDocumException, NotValidPassportException, HumanBiometricScanningException, BiometricVerificationFailedException, NotEnabledException, ConnectException {
        votingKiosk.setDocument('p');
        votingKiosk.readPassport();
        votingKiosk.readFaceBiometrics();
        votingKiosk.readFingerPrintBiometrics();

        BiometricData humanBD = votingKiosk.getHumanBioD();
        BiometricData passportBD = votingKiosk.getPasspBioD();

        assertEquals(humanBD.getFacialKey(), null);
        assertEquals(humanBD.getFingerPrintKey(), null);

        assertEquals(passportBD.getFacialKey(), null);
        assertEquals(passportBD.getFingerPrintKey(), null);
    }
}

