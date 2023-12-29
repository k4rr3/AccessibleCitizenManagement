package evoting;

import exceptions.ProceduralException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManualVotingKioskTest {
    private VotingKiosk votingKiosk;

    @BeforeEach
    public void setUp() {
        votingKiosk = new VotingKiosk();
    }
    @Test
    public void testProceduralInit () throws ProceduralException {
        votingKiosk.setOption(1);
        votingKiosk.initVoting();
        votingKiosk.setDocument('n');
        assertThrows(ProceduralException.class, () -> votingKiosk.initVoting());
    }
    @Test
    public void testInvalidOptions () throws ProceduralException {
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

}
