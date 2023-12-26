package data;

import exceptions.InvalidDNIDocumException;

final public class Nif {

    private String nif = null;

    public Nif(String nif) throws InvalidDNIDocumException{

        isValidNif(nif);
        this.nif = nif;
    }

    private void isValidNif(String nif) throws InvalidDNIDocumException {
        //Check if the NIF is not null and has the correct length
        if (nif == null || nif.length() != 9) {
            throw new InvalidDNIDocumException("NIF must have length 9");
        }
        // Check if the first eight characters are digits
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(nif.charAt(i))) {
                throw new InvalidDNIDocumException("The first 8 chars of the NIF must be digits.");
            }
        }
        // Check if the last character is a letter
        char lastChar = nif.charAt(8);
        if (!Character.isLetter(lastChar)) {
            throw new InvalidDNIDocumException("The last character of the NIF must be a letter.");
        }

        // Check if the letter is correct based on the first eight digits
        if (!isValidLetter(nif.substring(0, 8), lastChar)) {
            throw new InvalidDNIDocumException("The letter of the NIF is not valid.");
        }
    }

    private boolean isValidLetter(String digits, char expectedLetter) {
        // Perform the algorithm to calculate the expected letter based on the first eight digits
        // This is a simple example; you should replace it with the actual algorithm for calculating the letter.

        int value = Integer.parseInt(digits);
        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        char calculatedLetter = letters.charAt(value % 23);
        return calculatedLetter == expectedLetter;

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
        return nif.hashCode();
    }

    @Override
    public String toString() {
        return "Your NIF {" + "nif='" + nif + '\'' + '}';
    }

    //ToDo: Check if necessary getNif method
    public String getNif() {
        return nif;
    }
}
