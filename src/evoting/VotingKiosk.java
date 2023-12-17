package evoting;

import data.BiometricData;
import data.Nif;
import data.Password;
import data.VotingOption;
import exceptions.*;

/**
 * Internal classes involved in the exercise of the vote
 */
public class VotingKiosk {
    //  ??? The class members
    // ???The constructor/s
    // Input events
    private Nif nif;

    public VotingKiosk() {

    }

    public void initVoting() {

    }

    public void setDocument(char opt) {
    }

    public void enterAccount(String login, Password pssw) throws InvalidAccountException {
    }


    public void confirmIdentif(char conf) throws InvalidDNIDocumException {
    }


    public void enterNif(Nif nif) throws NotEnabledException, ConnectException {
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
    private void verifiyBiometricData
    (BiometricData humanBioD, BiometricData passpBioD)
            throws BiometricVerificationFailedException {
    }

    private void removeBiometricData() {
    }

    /*=================================================================================*/
    public void grantExplicitConsent(char cons) {
    }

    public void readPassport()
            throws NotValidPassportException, PassportBiometricReadingException {
    }

    public void readFaceBiometrics() throws HumanBiometricScanningException {
    }

    public void readFingerPrintBiometrics()
            throws NotEnabledException, HumanBiometricScanningException,
            BiometricVerificationFailedException, ConnectException {
    }


}