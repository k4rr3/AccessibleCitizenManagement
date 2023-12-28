package evoting;

import data.BiometricData;
import data.Nif;
import data.Password;
import data.VotingOption;
import evoting.biometricdataperipheral.HumanBiometricScanner;
import evoting.biometricdataperipheral.PassportBiometricReader;
import exceptions.*;
import mocks.StubElectoralOrganism;
import mocks.StubHumanBiometricScanner;
import mocks.StubPassportBiometricScanner;
import mocks.StubScrutiny;
import services.ElectoralOrganism;
import services.LocalService;
import services.Scrutiny;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Internal classes involved in the exercise of the vote
 */
public class VotingKiosk {
    private Nif nif;
    private BiometricData humanBioD;
    private BiometricData passpBioD;
    private byte[] fingerprintData;
    private byte[] faceData;
    private String passportNumber;
    private String extractedNif;
    private char explicitConsentGiven = 'n';
    private char opt;
    private final HashMap<String, String> supportUsers;
    private boolean enabledVoter;
    private boolean hasConnectivity;

    // ---------- Services variables -----------------------
    private ElectoralOrganism electoralOrganism;
    private LocalService localService;
    private Scrutiny scrutiny;


    // -------Biometric Data Peripheral----------------------
    private HumanBiometricScanner humanBiometricScanner;
    private PassportBiometricReader passportBiometricReader;
    // ------------------------------------------------------

    //  ??? The class members
    // ???The constructor/s
    // Input events


    public VotingKiosk(HashMap<String, String> supportUsers) {
        this.supportUsers = supportUsers;
    }

    //===============Setters for Dependency Injection==========================

    public void setScrutiny(Scrutiny scrutiny) {
        this.scrutiny = scrutiny;
    }

    public void setLocalService(LocalService localService) {
        this.localService = localService;
    }

    public void setElectoralOrganism(ElectoralOrganism electoralOrganism) {
        this.electoralOrganism = electoralOrganism;
    }

    public void setHumanBiometricScanner(HumanBiometricScanner humanBiometricScanner) {
        this.humanBiometricScanner = humanBiometricScanner;
    }

    public void setPassportBiometricReader(PassportBiometricReader passportBiometricReader) {
        this.passportBiometricReader = passportBiometricReader;
    }

    public char getExplicitConsentGiven() {
        return explicitConsentGiven;
    }

    public Nif getNif() {
        return nif;
    }

    public BiometricData getHumanBioD() {
        return humanBioD;
    }

    public BiometricData getPasspBioD() {
        return passpBioD;
    }

    public byte[] getFingerprintData() {
        return fingerprintData;
    }

    public byte[] getFaceData() {
        return faceData;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getExtractedNif() {
        return extractedNif;
    }

    public char getOpt() {
        return opt;
    }

    public HashMap<String, String> getSupportUsers() {
        return supportUsers;
    }

    public boolean isEnabledVoter() {
        return enabledVoter;
    }

    public boolean isHasConnectivity() {
        return hasConnectivity;
    }

    public ElectoralOrganism getElectoralOrganism() {
        return electoralOrganism;
    }

    public LocalService getLocalService() {
        return localService;
    }

    public Scrutiny getScrutiny() {
        return scrutiny;
    }

    public HumanBiometricScanner getHumanBiometricScanner() {
        return humanBiometricScanner;
    }

    public PassportBiometricReader getPassportBiometricReader() {
        return passportBiometricReader;
    }

    //======================================================================

    public void initVoting() throws ProceduralException {
        System.out.println("Seleccione la funcionalidad que desea:");
        System.out.println("1- e-voting \n 2- certificado de nacimiento 3- ...");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option == 0 || option > 4) {
            System.out.println("opción no válida");
        } else {
            System.out.println("funcionalidad e-voting seleccionada");
        }
        System.out.println("Acepta el consentimiento explícito del usuario ? \n Sí -> y\n No -> n\n default: n");
        char explicitConsent = scanner.next().charAt(0);
        grantExplicitConsent(explicitConsent);
        //Todo: hay que llamar a setDocument tras esto?? (porque es la siguiente acción en el DSS)
        //setDocument(opt)
    }

    public void grantExplicitConsent(char cons) throws ProceduralException {
        if (cons == 'y' || cons == 'n' || cons == 'Y' || cons == 'N') {
            this.explicitConsentGiven = cons;
        } else {
            throw new ProceduralException("Invalid explicit consent option");
        }
    }

    public void setDocument(char opt) throws ProceduralException {
        // check for a valid opt:
        // 'd' and 'n' stand for dni or nif which mean the same, but both are accepted
        // 'p' stands for passport
        if (opt == 'n' || opt == 'd' || opt == 'p') {
            this.opt = opt;
            System.out.println("Solicitando ayuda al personal de soporte...");
        } else {
            //Todo: shouldn't it be an exception, maybe ProceduralException or what??
            System.out.println("Incorrect document option was chosen");
            throw new ProceduralException("Incorrect document option was chosen");
        }
        //Todo: hay que llamar a enterAccount tras esto?? (porque es la siguiente acción en el DSS)
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
            //fixme: scanners shouldn't be used here!! due to DSS manual verification
            StubHumanBiometricScanner humanBiometricScanner = new StubHumanBiometricScanner();
            StubPassportBiometricScanner passportBiometricScanner = new StubPassportBiometricScanner("sample");
            //TODO: Comprobar identidad con HumanBiometricScanner y PassportBiometricScanner ???
            System.out.println("Biometric data verification successful. Identification confirmed.");
        } catch (InvalidDNIDocumException e) {
            throw new InvalidDNIDocumException("Biometric data verification failed. Identification not confirmed.");
        }
    }


    public void enterNif() throws NotEnabledException, ConnectException {
        //Todo: Checkear porque pone que el personal de soporte introduce manualmente el NIF
        if (nif.equals(this.nif) || enabledVoter) {
            if (hasConnectivity) {
                System.out.println("Successful nif insertion");
            } else {
                throw new ConnectException("Voter is in has connectivity issues");
            }
        } else {
            throw new NotEnabledException("Voter hasn't got a valid nif or is not enabled to vote");
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
            System.out.println("Not implemented yet, but finalize session has been invoked");
        }
        //(...) Setter methods for injecting dependences and additional methods
    }

    /*=================================================================================*/
    private void verifyBiometricData()
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

    /*====================VERIFICACIÓN BIOMÉTRICA=========================================*/


    public void readPassport()
            throws NotValidPassportException, PassportBiometricReadingException, InvalidDNIDocumException {
        passportBiometricReader = new StubPassportBiometricScanner(passportNumber);
        passportBiometricReader.validatePassport();
        passportBiometricReader.getPassportBiometricData(faceData, fingerprintData);
        passportBiometricReader.getNifWithOCR(extractedNif);


    }


    public void readFaceBiometrics() throws HumanBiometricScanningException {
        humanBiometricScanner = new StubHumanBiometricScanner();
        humanBiometricScanner.scanFaceBiometrics(faceData);
    }

    public void readFingerPrintBiometrics()
            throws NotEnabledException, HumanBiometricScanningException,
            BiometricVerificationFailedException, ConnectException {
        if (hasConnectivity) {
            if (enabledVoter) {
                humanBiometricScanner = new StubHumanBiometricScanner();
                electoralOrganism = new StubElectoralOrganism();
                humanBiometricScanner.scanFingerprintBiometrics(fingerprintData);
                BiometricData humanBioD = null;
                BiometricData passpBioD = null;
                verifyBiometricData();
                removeBiometricData();
                electoralOrganism.canVote(nif);
            } else {
                throw new NotEnabledException("Voter is not enabled to vote");
            }
        } else {
            throw new ConnectException("We're experiencing connectivity issues");
        }

    }


}