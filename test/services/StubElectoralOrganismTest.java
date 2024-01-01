package services;

import data.Nif;
import exceptions.ConnectException;
import exceptions.InvalidDNIDocumException;
import exceptions.NotEnabledException;
import mocks.StubElectoralOrganism;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StubElectoralOrganismTest {

    private StubElectoralOrganism electoralOrganism;

    @BeforeEach
    void setUp() {
        electoralOrganism = new StubElectoralOrganism();
    }

    @Test
    void testCanVoteForEnabledVoter() {
        try {
            electoralOrganism.canVote(new Nif("12345678Z"));
        } catch (Exception e) {
            fail("Exception not expected for an enabled voter");
        }
    }

    @Test
    void testCanVoteForDisabledVoter() {
        assertThrows(NotEnabledException.class, () -> electoralOrganism.canVote(new Nif("45678965E")));
    }

    @Test
    void testCanVoteWithConnectivityIssues() {
        electoralOrganism.setServerIsUp(false);

        assertThrows(ConnectException.class, () -> electoralOrganism.canVote(new Nif("12345678Z")));
    }

    @Test
    void testDisableVoter() {
        try {
            electoralOrganism.disableVoter(new Nif("12345678Z"));
        } catch (Exception e) {
            fail("Exception not expected for disabling a voter");
        }
    }

    @Test
    void testDisableVoterWithConnectivityIssues() {
        electoralOrganism.setServerIsUp(false);

        assertThrows(ConnectException.class, () -> electoralOrganism.disableVoter(new Nif("12345678Z")));
    }

    @Test
    void testDisableUnknownVoter() {
        //Attempt to disable a voter which doesn't exist in ElectoralOrganism's DB
        assertThrows(ConnectException.class, () -> electoralOrganism.disableVoter(new Nif("68514270F")));
    }
}
