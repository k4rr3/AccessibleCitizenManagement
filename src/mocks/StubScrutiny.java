package mocks;

import data.VotingOption;
import services.Scrutiny;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubScrutiny implements Scrutiny {

    private int totalValidVotes;
    private int nullVotes;
    private int blankVotes;

    private Map<VotingOption, Integer> validPartiesVoteCount;

    //FiXME: Add exceptions for invalid parties on each method

    private List<VotingOption> partyList = Arrays.asList(
            new VotingOption("Partido Popular"),
            new VotingOption("Partido Socialista Obrero Español"),
            new VotingOption("Unidas Podemos"),
            new VotingOption("Ciudadanos"),
            new VotingOption("Esquerra Republicana de Catalunya"),
            new VotingOption("Partido Nacionalista Vasco")
    );

    @Override
    public void initVoteCount(List<VotingOption> validParties) {
        validPartiesVoteCount = new HashMap<>();

        for (VotingOption party : validParties) {
            validPartiesVoteCount.put(party, 0);
        }

        totalValidVotes = 0;
        nullVotes = 0;
        blankVotes = 0;
    }

    @Override
    public void scrutinize(VotingOption vopt) {

        // Null representa la papeleta vacia por tanto voto en blanco
        if (vopt == null) {
            blankVotes++;
        }

        // La papeleta contiene un partido politico valido, se suma como voto al partido selecionado
        else if (validPartiesVoteCount.containsKey(vopt)) {
            int partyVoteCount = validPartiesVoteCount.get(vopt);
            validPartiesVoteCount.put(vopt, partyVoteCount + 1); // add one vote to the valid party

            totalValidVotes++;
        }

        // La papeleta contiene un partido politico invalido, se suma como voto nulo
        else {
            System.out.println("Invalid voting option: " + vopt);
            nullVotes++;
        }
    }

    @Override
    public int getVotesFor(VotingOption vopt) {
        if (validPartiesVoteCount.containsKey(vopt)) {
            return validPartiesVoteCount.get(vopt);
        } else {
            System.out.println("Invalid voting option: " + vopt);
        }
        return 0;
    }

    @Override
    public int getTotal() {
        return totalValidVotes;
    }

    @Override
    public int getNulls() {
        return nullVotes;
    }

    @Override
    public int getBlanks() {
        return blankVotes;
    }

    @Override
    public void getScrutinyResults() {
        // Display the results party by party
        for (Map.Entry<VotingOption, Integer> entry : validPartiesVoteCount.entrySet()) {
            System.out.println("Party: " + entry.getKey() + ", Votes: " + entry.getValue());
        }

        // Display the number of blank and null votes
        System.out.println("Blank Votes: " + blankVotes);
        System.out.println("Null Votes: " + nullVotes);
        System.out.println("Total Valid Votes: " + totalValidVotes);
    }
}
