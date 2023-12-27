package evoting;

import data.BiometricData;
import data.Nif;
import data.Password;
import data.VotingOption;
import exceptions.*;
import mocks.StubHumanBiometricScanner;
import mocks.StubPassportBiometricScanner;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Internal classes involved in the exercise of the vote
 */
public class VotingKiosk {
    private Nif nif;
    private BiometricData humanBioD;
    private BiometricData passpBioD;
    private char explicitConsentGiven = 'n';

    private boolean enabledVoter;
    private boolean hasConnectivity;

    //  ??? The class members
    // ???The constructor/s
    // Input events
    private char opt;

    //======================================================================
    private final HashMap<String, String> supportUsers;

    public VotingKiosk(HashMap<String, String> support) {
        this.supportUsers = support;
    }

    public void initVoting() {
        System.out.println("Seleccione la funcionalidad que desea:");
        System.out.println("1- e-voting \n 2- certificado de nacimiento 3- ...");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option == 0) {
            System.out.println("opción no válida");
        } else {
            System.out.println("funcionalidad e-voting seleccionada");
        }
        System.out.println("Acepta el consentimiento explícito del usuario ? \n Sí -> y\n No -> n\n default: n");
        char explicitConsent = scanner.next().charAt(0);
        grantExplicitConsent(explicitConsent);
    }

    public void setDocument(char opt) {
        this.opt = opt;
        System.out.println("Solicitando ayuda al personal de soporte...");
        //enterAccount(login, pssw)

    }

    //TODO: NO ES LO MISMO StubLocalService que la función enterAccount de VotingKiosk ???
    public void enterAccount(String login, Password pssw) throws InvalidAccountException {
        if (supportUsers.containsKey(login)) {
            // Check if the provided password matches the stored password
            String storedPassword = supportUsers.get(login);
            if (pssw != null && pssw.getPassword().equals(storedPassword)) {
                System.out.println("Authentication successful. Welcome, " + login + "!");
            } else {
                throw new InvalidAccountException("Authentication failed. Incorrect password.");
            }
        } else {
            throw new InvalidAccountException("Authentication failed. User not found.");
        }
    }


    public void confirmIdentif(char conf) throws InvalidDNIDocumException {
        if (explicitConsentGiven == 'n') {
            System.out.println("Explicit consent not given. Cannot confirm identification.");
            return;
        }

        try {
            nif = new Nif("a");
            // Assuming you have biometric data available
            StubHumanBiometricScanner humanBiometricScanner = new StubHumanBiometricScanner();
            StubPassportBiometricScanner passportBiometricScanner = new StubPassportBiometricScanner("sample");
            //TODO: Comprobar identidad con HumanBiometricScanner y PassportBiometricScanner ???
            System.out.println("Biometric data verification successful. Identification confirmed.");
        } catch (InvalidDNIDocumException e) {
            throw new InvalidDNIDocumException("Biometric data verification failed. Identification not confirmed.");
        }
    }


    public void enterNif(Nif nif) throws NotEnabledException, ConnectException {
        if (nif.equals(this.nif) || ){

        }
    }

    public void initOptionsNavigation() {
    }

    public void consultVotingOption(VotingOption vopt) {
    }

    public void vote() {
    }

    public void confirmVotingOption(char conf) throws ConnectException {
    }

    // Internal operation, not required
    private void finalizeSession() {
        {
        }
        //(...) Setter methods for injecting dependences and additional methods
    }

    /*=================================================================================*/
    private void verifyBiometricData(BiometricData humanBioD, BiometricData passpBioD)
            throws BiometricVerificationFailedException {
        if (!humanBioD.equals(passpBioD)) {
            removeBiometricData();
            throw new BiometricVerificationFailedException("Biometric data from passport doesn't match human data");
        }

    }

    private void removeBiometricData() {
        humanBioD.deleteAllInfo();
        passpBioD.deleteAllInfo();
    }

    /*=================================================================================*/
    public void grantExplicitConsent(char cons) {
        this.explicitConsentGiven = cons;
    }

    public void readPassport(String passportNumber, String extractedNif, byte[] facialData, byte[] fingerprintData)
            throws NotValidPassportException, PassportBiometricReadingException {
        StubPassportBiometricScanner passportBiometricScanner = new StubPassportBiometricScanner(passportNumber);
        try {
            passportBiometricScanner.getNifWithOCR(extractedNif);
            passportBiometricScanner.validatePassport();
        } catch (Exception e) {
            throw new NotValidPassportException(e.getMessage());
        }
        passportBiometricScanner.getPassportBiometricData(facialData, fingerprintData);

    }

    public void readFaceBiometrics(byte[] faceData) throws HumanBiometricScanningException {
        StubHumanBiometricScanner humanBiometricScanner = new StubHumanBiometricScanner();
        humanBiometricScanner.scanFaceBiometrics(faceData);
    }

    public void readFingerPrintBiometrics(byte[] fingerprintData)
            throws NotEnabledException, HumanBiometricScanningException,
            BiometricVerificationFailedException, ConnectException {
        StubHumanBiometricScanner humanBiometricScanner = new StubHumanBiometricScanner();
        humanBiometricScanner.scanFingerprintBiometrics(fingerprintData);
        /*TODO: MISSING CODE TO THROW NotEnabledException and ConnectException para indicar que el votante ya ha votado o no está en un colegio electoral que le corresponde.*/
        /*TODO: MISSING CODE TO THROW BiometricVerificationFailedException and HumanBiometricScanningException*/
        BiometricData humanBioD = null;
        BiometricData passpBioD = null;
        verifyBiometricData(humanBioD, passpBioD);
    }


}