package services;
import data.VotingOption;

public interface Scrutiny { // Centralizes the vote counting
    //void initVoteCount (List<VotingOption> validParties); Todo: List ??
    void scrutinize (VotingOption vopt);
    int getVotesFor (VotingOption vopt);
    int getTotal ();
    int getNulls ();
    int getBlanks ();
    void getScrutinyResults ();
}