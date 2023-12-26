package mocks;

import data.Nif;
import exceptions.ConnectException;
import exceptions.NotEnabledException;
import services.ElectoralOrganism;

public class StubElectoralOrganism implements ElectoralOrganism {

    private boolean voterEnabled = true; //turn to false when voter has already voted or is in an incorrect electoral college
    private boolean hasConnectivity = true;

    @Override
    public void canVote(Nif nif) throws NotEnabledException, ConnectException {
        // Check if the voter is enabled to vote
        if (voterEnabled) {
            // Additional conditions for eligibility to vote can be added here
            // For example, check if the voter is in the correct electoral college
            // ...
            if (hasConnectivity) {


                // If all conditions are met, the voter can vote
                System.out.println("Voter with NIF " + nif.getNif() + " is eligible to vote.");
            } else {
                throw new ConnectException("No connectivity detected");
            }
        } else {
            throw new NotEnabledException("Voter is not enabled to vote.");
        }
    }

    @Override
    public void disableVoter(Nif nif) throws ConnectException {
        // Simulate disabling the voter (mark them as voted)
        if (hasConnectivity) {
            System.out.println("Voter with NIF " + nif.getNif() + " has voted. Disabling voter.");

            // Set the voter as disabled
            voterEnabled = false;
        } else {
            throw new ConnectException("No connectivity detected");
        }
    }
}
