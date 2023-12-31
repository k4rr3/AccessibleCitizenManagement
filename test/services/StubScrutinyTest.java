package services;

import data.VotingOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mocks.StubScrutiny;

import static org.junit.jupiter.api.Assertions.*;

class StubScrutinyTest {

    private StubScrutiny scrutiny;

    @BeforeEach
    void setUp() {
        scrutiny = new StubScrutiny();
    }

    @Test
    void testInitVoteCount() {
        scrutiny.initVoteCount(null);
        assertEquals(0, scrutiny.getTotal());
        assertEquals(0, scrutiny.getNulls());
        assertEquals(0, scrutiny.getBlanks());
    }

    @Test
    void testScrutinizeValidVote() {
        VotingOption validParty = new VotingOption("Partido Popular");

        scrutiny.scrutinize(validParty);

        assertEquals(1, scrutiny.getTotal());
        assertEquals(0, scrutiny.getNulls());
        assertEquals(0, scrutiny.getBlanks());
        assertEquals(1, scrutiny.getVotesFor(validParty));
    }

    @Test
    void testScrutinizeBlankVote() {
        scrutiny.scrutinize(null);

        assertEquals(1, scrutiny.getTotal());
        assertEquals(0, scrutiny.getNulls());
        assertEquals(1, scrutiny.getBlanks());
    }

    @Test
    void testScrutinizeInvalidVote() {
        VotingOption invalidParty = new VotingOption("Partido ficticio");

        scrutiny.scrutinize(invalidParty);

        assertEquals(1, scrutiny.getTotal());
        assertEquals(1, scrutiny.getNulls());
        assertEquals(0, scrutiny.getBlanks());
    }

    @Test
    void testGetVotesForValidParty() {
        VotingOption validParty = new VotingOption("Partido Popular");
        scrutiny.scrutinize(validParty);

        assertEquals(1, scrutiny.getVotesFor(validParty));
    }

    @Test
    void testGetVotesForInvalidParty() {
        VotingOption invalidParty = new VotingOption("Partido ficticio");

        assertEquals(0, scrutiny.getVotesFor(invalidParty));
    }

    @Test
    void testGetTotalValidVotes() {
        VotingOption validParty = new VotingOption("Partido Popular");
        scrutiny.scrutinize(validParty);
        scrutiny.scrutinize(null);

        assertEquals(1, scrutiny.getTotal()-scrutiny.getBlanks()-scrutiny.getNulls());
    }

    @Test
    void testGetNulls() {
        scrutiny.scrutinize(new VotingOption("Partido Ficticio"));
        scrutiny.scrutinize(new VotingOption("Partido Ficticio"));

        assertEquals(2, scrutiny.getNulls());
    }

    @Test
    void testGetBlanks() {
        scrutiny.scrutinize(null);
        scrutiny.scrutinize(null);

        assertEquals(2, scrutiny.getBlanks());
    }
}
