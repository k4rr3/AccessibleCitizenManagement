package data;

/**
 * Essential data classes
 */
final public class VotingOption {

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
        return party.equals(vO.party);
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