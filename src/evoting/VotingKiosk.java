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

    private int proceduralStep;

    private final Scanner scanner;

    //  ??? The class members
    // ???The constructor/s
    // Input events


    public VotingKiosk(HashMap<String, String> supportUsers) {
        this.supportUsers = supportUsers;
        this.proceduralStep = 1;
        this.scanner = new Scanner(System.in);
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
        if (proceduralStep != 1) throw new ProceduralException("Voting procedure has already been started");
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
        proceduralStep++;

    }


    public void setDocument(char opt) throws ProceduralException {
        if (proceduralStep != 2) throw new ProceduralException("Voting procedure has already been started");
        // check for a valid opt:
        // 'd' and 'n' stand for dni or nif which mean the same, but both are accepted
        // 'p' stands for passport

        if (opt == 'n' || opt == 'd') {
            this.opt = opt;
            System.out.println("Solicitando ayuda al personal de soporte...");
        } else if (opt == 'p') {
            char explicitConsent = scanner.next().charAt(0);
            grantExplicitConsent(explicitConsent);
        } else {
            throw new ProceduralException("Incorrect document option was chosen");
        }
    }

    public void grantExplicitConsent(char cons) throws ProceduralException {
        if (proceduralStep != 2) throw new ProceduralException("Some procedures went wrong");
        if (cons == 'y' || cons == 'n' || cons == 'Y' || cons == 'N') {
            this.explicitConsentGiven = cons;
        } else {
            throw new ProceduralException("Invalid explicit consent option");
        }
    }

    public void enterAccount(String login, Password pssw) throws InvalidAccountException, ProceduralException {
        if (proceduralStep != 2) throw new ProceduralException("Some procedures went wrong");
        if (this.opt == 'n' || this.opt == 'd') {
            //Estamos en el primer DSS
            System.out.println("Verifying account...");
            localService.verifyAccount(login, pssw);
            System.out.println("Account successfully verified");
        } else {
            //Estamos en el segundo DSS
        }

    }


    public void confirmIdentif(char conf) throws InvalidDNIDocumException, ProceduralException {
        if (this.opt == 'n' || this.opt == 'd') {
            if (conf == 'F') throw new InvalidDNIDocumException("Unconfirmed identity");

        } else throw new ProceduralException("Wrong identification method");
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
        passportBiometricReader.validatePassport();
        passportBiometricReader.getPassportBiometricData();
        passportBiometricReader.getNifWithOCR();


    }


    public void readFaceBiometrics() throws HumanBiometricScanningException {
        humanBiometricScanner.scanFaceBiometrics(faceData);
    }

    public void readFingerPrintBiometrics()
            throws NotEnabledException, HumanBiometricScanningException,
            BiometricVerificationFailedException, ConnectException {
        if (hasConnectivity) {
            if (enabledVoter) {
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