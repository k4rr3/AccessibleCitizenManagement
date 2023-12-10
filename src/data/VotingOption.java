package data;

/**
 * Essential data classes
 */
final public class VotingOption {
    // The tax identification number in the Spanish state.

    //Todo: define null and blank votes
    private final String party;

    public VotingOption(String option) {
        this.party = option;
    }

    public String getParty() {
        return party;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotingOption vO = (VotingOption) o;
        //return party.equals(niff.party);
        return true;
    }

    @Override
    public int hashCode() {
        return party.hashCode();
    }

    @Override
    public String toString() {
        return "Vote option {" + "party='" + party + '\'' + '}';
    }
}