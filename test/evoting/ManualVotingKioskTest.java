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

    @Test
    public void testEnterAccountSuccess() throws ProceduralException, InvalidAccountException {
        votingKiosk.setDocument('n');
        votingKiosk.setOpt('n');
        assertDoesNotThrow(InvalidAccountException.class, votingKiosk.enterAccount("login",new Password("s")));



    }


}
