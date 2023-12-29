package evoting;

import data.Password;
import exceptions.InvalidAccountException;
import exceptions.ProceduralException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ManualVotingKioskTest {
    private VotingKiosk votingKiosk;

    @BeforeEach
    public void setUp() throws ProceduralException {
        votingKiosk = new VotingKiosk();
        votingKiosk.setOption(1);
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
        System.out.println("");
        votingKiosk.setOption(5);
        assertThrows(ProceduralException.class, () -> votingKiosk.initVoting());
    }

    // ============ Setdocument Tests ==================================
    @Test
    public void testSetDocumentIncorrectOpt() throws ProceduralException {
        votingKiosk.setOption(1);
        votingKiosk.initVoting();
        assertThrows(ProceduralException.class, () -> votingKiosk.setDocument('a'));
    }

    @Test
    public void testSetDocumentCorrectOpt() throws ProceduralException {
        votingKiosk.setOption(1);
        votingKiosk.initVoting();
        assertDoesNotThrow(() -> votingKiosk.setDocument('n'));
        assertDoesNotThrow(() -> votingKiosk.setDocument('d'));
    }

    @Test
    public void testEnterAccountSuccess() throws ProceduralException, InvalidAccountException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        assertDoesNotThrow(() -> votingKiosk.enterAccount("alice", new Password("Alice1234")));
    }

    @Test
    public void testEnterAccountFailure() throws ProceduralException, InvalidAccountException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        assertDoesNotThrow(() -> votingKiosk.enterAccount("alice", new Password("Alice13443")));
    }


}
