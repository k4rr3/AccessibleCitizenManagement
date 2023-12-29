package evoting;

import data.BiometricData;
import data.Nif;
import data.Password;
import data.VotingOption;
import evoting.biometricdataperipheral.HumanBiometricScanner;
import evoting.biometricdataperipheral.PassportBiometricReader;
import exceptions.*;
import mocks.StubElectoralOrganism;
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


    private VotingOption votingOption;

    // ---------- Services variables -----------------------
    private ElectoralOrganism electoralOrganism;
    private LocalService localService;
    private Scrutiny scrutiny;


    // -------Biometric Data Peripheral----------------------
    private HumanBiometricScanner humanBiometricScanner;
    private PassportBiometricReader passportBiometricReader;
    // ------------------------------------------------------

    private int manualProcedureStep;
    private int biomProcedureStep;

    private final Scanner scanner;

    //  ??? The class members
    // ???The constructor/s
    // Input events


    public VotingKiosk() {
        this.manualProcedureStep = 1;
        this.scanner = new Scanner(System.in);
    }

    //==============Procedural Counters & Methods==============================
    private void checkManualStep(int stepNumber) throws ProceduralException {
        if ((opt == 'd' || opt == 'n') && manualProcedureStep != stepNumber)
            throw new ProceduralException("Some procedures went wrong");

    }

    private void checkBiomStep(int stepNumber) throws ProceduralException {
        if (opt == 'p' && biomProcedureStep != stepNumber)
            throw new ProceduralException("Some procedures went wrong");
    }

    private void incManualStep() {
        if (opt == 'd' || opt == 'n') manualProcedureStep++;
    }

    private void incBiomStep() {
        if (opt == 'p') biomProcedureStep++;
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


    //======================================================================

    public void initVoting() throws ProceduralException {
        checkManualStep(1);
        checkBiomStep(1);
        System.out.println("Seleccione la funcionalidad que desea:");
        System.out.println("1- e-voting \n 2- certificado de nacimiento 3- ...");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option == 0 || option > 4) {
            System.out.println("opción no válida");
        } else {
            System.out.println("funcionalidad e-voting seleccionada");
        }
        incManualStep();
        incBiomStep();

    }


    public void setDocument(char opt) throws ProceduralException {
        checkManualStep(2);
        checkBiomStep(2);
        // check for a valid opt:
        // 'd' and 'n' stand for dni or nif which mean the same, but both are accepted
        // 'p' stands for passport

        if (opt == 'n' || opt == 'd') {
            this.opt = opt;
            System.out.println("Solicitando ayuda al personal de soporte...");
            incManualStep();
        } else if (opt == 'p') {
            char explicitConsent = scanner.next().charAt(0);
            grantExplicitConsent(explicitConsent);
            incBiomStep();
        } else {
            throw new ProceduralException("Incorrect document option was chosen");
        }
    }

    public void grantExplicitConsent(char cons) throws ProceduralException {
        checkBiomStep(999999);
        if (cons == 'y' || cons == 'n' || cons == 'Y' || cons == 'N') {
            this.explicitConsentGiven = cons;
            incBiomStep();
        } else {
            throw new ProceduralException("Invalid explicit consent option");
        }
    }

    public void enterAccount(String login, Password pssw) throws InvalidAccountException, ProceduralException {
        checkManualStep(3);
        if (this.opt == 'n' || this.opt == 'd') {
            //Estamos en el primer DSS
            System.out.println("Verifying account...");
            localService.verifyAccount(login, pssw);
            System.out.println("Account successfully verified");
            incManualStep();
        }

    }


    public void confirmIdentif(char conf) throws InvalidDNIDocumException, ProceduralException {
        if (this.opt == 'n' || this.opt == 'd') {
            checkManualStep(4);
            if (conf == 'F') throw new InvalidDNIDocumException("Unconfirmed identity");
            System.out.println("Identity has been successfully confirmed");
            incManualStep();
        } else throw new ProceduralException("Wrong identification method");
    }


    public void enterNif(Nif nif) throws NotEnabledException, ConnectException, ProceduralException {
        if (this.opt == 'n' || this.opt == 'd') {
            checkManualStep(5);
            this.nif = nif;
            System.out.println("NIF is ok, now let's check vote's right");
            electoralOrganism.canVote(nif);
            System.out.println("Citizen can vote");
            incManualStep();
        }
        throw new ProceduralException("Wrong identification method");

    }


    public void initOptionsNavigation() throws ProceduralException {
        checkManualStep(6);
        checkBiomStep(9);
        System.out.println("Desplegando menús y opciones de voto.....");
        incManualStep();
        incBiomStep();
    }

    public void consultVotingOption(VotingOption vopt) throws ProceduralException {
        checkManualStep(7);
        checkBiomStep(10);
        System.out.println("Mostrando información del partido " + vopt.getParty());
        this.votingOption = vopt;
        incManualStep();
        incBiomStep();
    }

    public void vote() throws ProceduralException {
        checkManualStep(8);
        checkBiomStep(11);
        System.out.println("Votando por " + votingOption.getParty());
        System.out.println("Mostrando pantalla de confirmación de voto");
        incManualStep();
        incBiomStep();
    }

    public void confirmVotingOption(char conf) throws ConnectException, ProceduralException {
        checkManualStep(9);
        checkBiomStep(12);
        if (conf == 'y' || conf == 'Y') {
            System.out.println("Confirmando opción de voto: " + votingOption.getParty());
            System.out.println("Voto en proceso....");
            scrutiny.scrutinize(votingOption);
            System.out.println("voto escrutado");
            electoralOrganism.disableVoter(nif);
            System.out.println("Votante deshabilitado correctamente");
            finalizeSession();
            System.out.println("Mostrando pantalla inicial...");
        } else if (conf == 'n' || conf == 'N') {
            System.out.println("Rechazando la opción de voto: " + votingOption.getParty());
            throw new ProceduralException("La opción de voto ha sido rechazada");
        }
        incManualStep();
        incBiomStep();

    }

    // Internal operation, not required
    private void finalizeSession() {
        System.out.println("Not implemented yet, but finalize session has been invoked");
    }
//(...) Setter methods for injecting dependences and additional methods


    /*=================================================================================*/
    /*private void verifyBiometricData()
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

    *//*====================VERIFICACIÓN BIOMÉTRICA=========================================*//*


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

*/
}