package mocks;

import data.Nif;
import exceptions.ConnectException;
import exceptions.NotEnabledException;
import services.ElectoralOrganism;

import java.util.HashMap;

public class StubElectoralOrganism implements ElectoralOrganism {

    private HashMap<Nif, Boolean> enabledVoter = new HashMap<>() {{
        try {
            put(new Nif("12345678Z"), true);
            put(new Nif("22334455Y"), true);
            put(new Nif("45678965E"), false);
            put(new Nif("45678964K"), false);
            put(new Nif("99572958R"), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }};


    private boolean serverIsUp = true;

    public void setServerIsUp(boolean serverIsUp) {
        this.serverIsUp = serverIsUp;
    }

    @Override
    public void canVote(Nif nif) throws NotEnabledException, ConnectException {
        // Check if the voter is enabled to vote
        if (enabledVoter.containsKey(nif) && enabledVoter.get(nif)) {
            if (serverIsUp) {

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
        if (serverIsUp && enabledVoter.containsKey(nif)) {

            System.out.println("Voter with NIF " + nif.getNif() + " has voted. Disabling voter.");
            enabledVoter.put(nif, false);
        } else {
            throw new ConnectException("No connectivity detected or voter not registered in ElectoralOrganism's DB");
        }
    }

}
