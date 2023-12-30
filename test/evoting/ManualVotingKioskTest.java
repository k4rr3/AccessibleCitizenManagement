package evoting;

import data.Nif;
import data.Password;
import data.VotingOption;
import exceptions.*;
import mocks.StubElectoralOrganism;
import mocks.StubLocalService;
import mocks.StubScrutiny;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.LocalService;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ManualVotingKioskTest {
    private VotingKiosk votingKiosk;

    @BeforeEach
    public void setUp() throws ProceduralException {
        votingKiosk = new VotingKiosk();
        votingKiosk.setLocalService(new StubLocalService());
        votingKiosk.setOption(1);
        votingKiosk.setOpt('n');
        votingKiosk.initVoting();
    }

    @Test
    public void testProceduralInit() throws ProceduralException {
        votingKiosk.setDocument('n');
        assertThrows(ProceduralException.class, () -> votingKiosk.initVoting());
    }

    @Test
    public void testInvalidOptions() {
        votingKiosk.setOption(0);
        assertThrows(ProceduralException.class, () -> votingKiosk.initVoting());
        votingKiosk.setOption(5);
        assertThrows(ProceduralException.class, () -> votingKiosk.initVoting());
    }

    // ============ Set document Tests ==================================
    @Test
    public void testSetDocumentIncorrectOpt() {
        assertThrows(ProceduralException.class, () -> votingKiosk.setDocument('a'));
    }

    @Test
    public void testSetDocumentCorrectOpt() {
        assertDoesNotThrow(() -> votingKiosk.setDocument('n'));
    }

    @Test
    public void testEnterAccountSuccess() throws ProceduralException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.setLocalService(new StubLocalService());
        assertDoesNotThrow(() -> votingKiosk.enterAccount("bob", new Password("Bob59678")));
    }

    @Test
    public void testEnterAccountFailure() throws ProceduralException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.setLocalService(new StubLocalService());
        assertThrows(IllegalArgumentException.class, () -> votingKiosk.enterAccount("alice", new Password("hola")));
    }

    @Test
    public void testConfirmIdentifSuccess() throws ProceduralException, InvalidAccountException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.enterAccount("bob", new Password("Bob59678"));
        assertDoesNotThrow(() -> votingKiosk.confirmIdentif('S'));
    }

    @Test
    public void testConfirmIdentifFailure() throws ProceduralException, InvalidAccountException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.enterAccount("bob", new Password("Bob59678"));
        assertThrows(InvalidDNIDocumException.class, () -> votingKiosk.confirmIdentif('F'));
    }

    @Test
    public void testConfirmIdentifOptFailure() throws ProceduralException, InvalidAccountException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.enterAccount("bob", new Password("Bob59678"));
        votingKiosk.setOpt('p');
        assertThrows(ProceduralException.class, () -> votingKiosk.confirmIdentif('F'));
    }

    @Test
    public void testEnterNifSuccess() throws ProceduralException, InvalidAccountException, InvalidDNIDocumException, NotEnabledException, ConnectException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.enterAccount("bob", new Password("Bob59678"));
        votingKiosk.confirmIdentif('S');
        votingKiosk.setElectoralOrganism(new StubElectoralOrganism());
        assertDoesNotThrow(() -> votingKiosk.enterNif(new Nif("12345678Z")));
    }

    @Test
    public void testEnterNifFailure() throws ProceduralException, InvalidAccountException, InvalidDNIDocumException, NotEnabledException, ConnectException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.enterAccount("bob", new Password("Bob59678"));
        votingKiosk.confirmIdentif('S');
        votingKiosk.setElectoralOrganism(new StubElectoralOrganism());
        assertThrows(InvalidDNIDocumException.class, () -> votingKiosk.enterNif(new Nif("12345678X")));
    }

    @Test
    public void testconfirmVotingOptionSuccess() throws ProceduralException, InvalidAccountException, InvalidDNIDocumException, NotEnabledException, ConnectException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.enterAccount("bob", new Password("Bob59678"));
        votingKiosk.confirmIdentif('S');
        votingKiosk.setElectoralOrganism(new StubElectoralOrganism());
        votingKiosk.enterNif(new Nif("12345678Z"));
        votingKiosk.initOptionsNavigation();
        votingKiosk.consultVotingOption(new VotingOption("Unidas Podemos"));
        votingKiosk.vote();
        votingKiosk.setScrutiny(new StubScrutiny());
        votingKiosk.setElectoralOrganism(new StubElectoralOrganism());
        assertDoesNotThrow(() -> votingKiosk.confirmVotingOption('Y'));
    }

    @Test
    public void testconfirmVotingOptionFailure() throws ProceduralException, InvalidAccountException, InvalidDNIDocumException, NotEnabledException, ConnectException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.enterAccount("bob", new Password("Bob59678"));
        votingKiosk.confirmIdentif('S');
        votingKiosk.setElectoralOrganism(new StubElectoralOrganism());
        votingKiosk.enterNif(new Nif("12345678Z"));
        votingKiosk.initOptionsNavigation();
        votingKiosk.consultVotingOption(new VotingOption("Unidas Podemos"));
        votingKiosk.vote();
        votingKiosk.setScrutiny(new StubScrutiny());
        votingKiosk.setElectoralOrganism(new StubElectoralOrganism());
        assertThrows(ProceduralException.class, () -> votingKiosk.confirmVotingOption('N'));
    }

}
