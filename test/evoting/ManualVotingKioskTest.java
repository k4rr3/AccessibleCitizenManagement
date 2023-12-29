package evoting;

import data.Password;
import exceptions.InvalidAccountException;
import exceptions.ProceduralException;
import mocks.StubLocalService;
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
    public void testInvalidOptions() throws ProceduralException {
        votingKiosk.setOption(0);
        assertThrows(ProceduralException.class, () -> votingKiosk.initVoting());
        votingKiosk.setOption(5);
        assertThrows(ProceduralException.class, () -> votingKiosk.initVoting());
    }

    // ============ Set document Tests ==================================
    @Test
    public void testSetDocumentIncorrectOpt() throws ProceduralException {
        assertThrows(ProceduralException.class, () -> votingKiosk.setDocument('a'));
    }

    @Test
    public void testSetDocumentCorrectOpt() throws ProceduralException {

        assertDoesNotThrow(() -> votingKiosk.setDocument('n'));
    }

    @Test
    public void testEnterAccountSuccess() throws ProceduralException, InvalidAccountException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.setLocalService(new StubLocalService());
        assertDoesNotThrow(() -> votingKiosk.enterAccount("bob", new Password("Bob59678")));
    }

    @Test
    public void testEnterAccountFailure() throws ProceduralException, InvalidAccountException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        votingKiosk.setLocalService(new StubLocalService());
        assertThrows(IllegalArgumentException.class, () -> votingKiosk.enterAccount("alice", new Password("hola")));
    }


}
